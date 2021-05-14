package com.example.team31.ui.overview.week_overview


import android.content.ContentValues.TAG
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

    // detailed fragment data
    private val selected = MutableLiveData<RefinedForecast>()
    val ref = FirebaseDatabase.getInstance().getReference("Users")


    fun select(item: RefinedForecast) {
        selected.value = item
    }
    fun getSelected() = selected.value


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

            //val ref = FirebaseDatabase.getInstance().getReference("Users")
            val brukere = ArrayList<Bruker>()
            var mainUser = Bruker()

            // Henter brukere fra firebase
            val UserListener = object : ValueEventListener {
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
            ref.addValueEventListener(UserListener)
        return mainUser
    }
    //fun getMainUser():Bruker{
      //  return mainUser
    //}

}

fun checkCreatedAlerts(date:String, alertList: MutableList<Varsel>):Boolean{
    for ( i in alertList){
        if (i.date == date) return true
    }
    return false
}

fun getNumberOfAlerts(date: String, alertList: MutableList<Varsel>):Int{
    return alertList.filter { it.date == date }.count()
}



fun getAcceptedShifts(date: String, accepted: MutableList<Varsel>):Int{
    return accepted.filter { it.date == date }.count()
}

fun checkLowStaffing(forecast: RefinedForecast, max: String?):Boolean{
    println("check low staffing:" +  forecast.temp.toDouble())
    return (forecast.temp.toDouble() >= max!!.toDouble() && forecast.precipitation!!.toDouble() <= 0.2)
}

fun checkStaffingDemand(forecast: RefinedForecast, user: Bruker): Int{
    var manko = 0.0
    val maxTemp = 30 //assumption
    if (forecast.temp.toDouble() < maxTemp.toDouble()) {
        val x = user.maxBemanning!!.toDouble() - user.normalBemanning!!.toDouble()
        val y = forecast.temp.toDouble() - user.triggerTemp!!.toDouble()
        val z = maxTemp.toDouble() - user.triggerTemp!!.toDouble()
        val xy = x*y
        manko = xy / z
        if (0.0< manko && manko <1.0){
            manko = 1.0
        }
    } else {
        manko = user.maxBemanning!!.toDouble() - user.normalBemanning!!.toDouble()
    }
    return manko.toInt()
}

suspend fun getAvailableShiftsList(userId:String): MutableList<Varsel> {
    val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("not_Taken")
    val liste = mutableListOf<Varsel>()
    val alertListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if (dataSnapshot.exists()) {
                for (i in dataSnapshot.children) {
                    val varsel = i.getValue(Varsel::class.java)
                    liste.add(varsel!!)
                }
            }

        }
        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("message", "loadPost:onCancelled", databaseError.toException())
        }
    }
    ref.addValueEventListener(alertListener)
    delay(200)
    return liste
}
suspend fun getAcceptedShiftsList(userId:String): MutableList<Varsel> {
    val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("Taken")
    val liste = mutableListOf<Varsel>()
    val alertListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if (dataSnapshot.exists()) {
                for (i in dataSnapshot.children) {
                    val varsel = i.getValue(Varsel::class.java)
                    liste.add(varsel!!)
                }
            }

        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("message", "loadPost:onCancelled", databaseError.toException())
        }
    }
    ref.addValueEventListener(alertListener)
    delay(200)
    return liste
}