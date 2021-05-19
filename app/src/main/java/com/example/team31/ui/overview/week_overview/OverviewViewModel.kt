package com.example.team31.ui.overview.week_overview


import android.util.Log
import androidx.lifecycle.*
import com.example.team31.Bruker
import com.example.team31.Varsel

import com.example.team31.data.repositories.ForecastRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class OverviewViewModel @Inject constructor(
        private val repository: ForecastRepository): ViewModel() {

    private val _forecastListModelLiveData = MutableLiveData<List<RefinedForecast>>()
    val forecastList: LiveData<List<RefinedForecast>>
        get() = _forecastListModelLiveData

    private val ref = FirebaseDatabase.getInstance().getReference("Users")

    fun getForecastList(lat: String, lon: String) {
        viewModelScope.launch {
            val result = repository.fetchLocationForecast(lat, lon)
            //val result = service.fetchLocationForecast(lat,lon)
            Log.d("result", "$result")

            withContext(Dispatchers.Main) {
                val forecastList = repository.createForecast(result)
                println(forecastList)
                _forecastListModelLiveData.value = forecastList
            }
        }
    }

    fun getUser(id: String):Bruker {

            val brukere = ArrayList<Bruker>()
            var mainUser = Bruker()

            // Henter brukere fra firebase
            val userListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (i in dataSnapshot.children) {
                            val user = i.getValue(Bruker::class.java)
                            brukere.add(user!!)
                            Log.i("bruker", user.toString())
                            if (user.id == id) {
                                mainUser = user
                            }
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w("message", "loadPost:onCancelled", databaseError.toException())
                }
            }
            ref.addValueEventListener(userListener)
        return mainUser
    }
    suspend fun getAvailableShiftsList(userId:String): MutableList<Varsel> {
        val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("not_Taken")
        val list = mutableListOf<Varsel>()
        val alertListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (i in dataSnapshot.children) {
                        val shift = i.getValue(Varsel::class.java)
                        list.add(shift!!)
                    }
                }

            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("message", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(alertListener)
        delay(800)
        return list
    }
    suspend fun getAcceptedShiftsList(userId:String): MutableList<Varsel> {
        val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("Taken")
        val list = mutableListOf<Varsel>()
        val alertListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (i in dataSnapshot.children) {
                        val alert = i.getValue(Varsel::class.java)
                        list.add(alert!!)
                    }
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("message", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(alertListener)
        delay(800)
        return list
    }
}

fun checkCreatedAlerts(date:String, alertList: MutableList<Varsel>):Boolean{
    for ( i in alertList){
        if (i.date == date) return true
    }
    return false
}
fun checkAcceptedAlerts(date: String, accepted: MutableList<Varsel>):Boolean{
    for (i in accepted){
        if (i.date == date) return true
    }
    return false
}

fun getNumberOfAlerts(date: String, alertList: MutableList<Varsel>):Int{
    return alertList.filter { it.date == date }.count()
}
fun getcreatedTotal(date: String, alertList: MutableList<Varsel>, acceptedList: MutableList<Varsel>):Int{
    val accepted = acceptedList.filter { it.date == date }.count()
    val notAccepted = alertList.filter { it.date == date }.count()
    return accepted + notAccepted
}

fun getAcceptedShifts(date: String, acceptedList: MutableList<Varsel>):Int{
    return acceptedList.filter { it.date == date }.count()
}

fun checkLowStaffing(forecast: RefinedForecast, max: String?, precipitation: Boolean):Boolean{
    println("check low staffing:" +  forecast.temp.toDouble())
    return if(precipitation){
        (forecast.temp.toDouble() >= max!!.toDouble() && forecast.precipitation!!.toDouble() <= 0.5)
    }else{
        (forecast.temp.toDouble() >= max!!.toDouble())
    }

}

fun checkStaffingDemand(forecast: RefinedForecast, user: Bruker): Int{
    var manko: Double
    var maxTemp = 30 //assumption
    if (maxTemp  < user.triggerTemp!!.toInt()){
        maxTemp = (1.2*user.triggerTemp!!.toDouble()).toInt()
    }
    if (forecast.temp.toDouble() < maxTemp.toDouble()) {
        val x = user.maxBemanning!!.toDouble() - user.normalBemanning!!.toDouble()
        val y = forecast.temp.toDouble() - user.triggerTemp!!.toDouble()
        val z = maxTemp.toDouble() - user.triggerTemp!!.toDouble()
        val xy = x*y
        manko = xy / z
        if (0.0<= manko && manko <1.0){
            manko = 1.0
        }
    } else {
        manko = user.maxBemanning!!.toDouble() - user.normalBemanning!!.toDouble()
    }
    return manko.toInt()
}

