package com.example.team31.ui.ansatt_interface.available_shifts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team31.Varsel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AvailableShiftsListViewModel : ViewModel() {

    private val _alertList = MutableLiveData<List<Varsel>>()
    val alertList: LiveData<List<Varsel>>
        get() = _alertList

    fun getMyAlerts(userId: String, ansattId: String){
        viewModelScope.launch {
            val recentAccepted = getAcceptedShifts(userId)
            val responseAlerts = getAlertList(userId, recentAccepted, ansattId).filter { !(hasShift(recentAccepted,it.date!!, ansattId)) }
                    .distinctBy { it.date } as MutableList<Varsel>


            withContext(Dispatchers.Main){
                _alertList.value =responseAlerts
            }
        }
    }

    // Gets all varsel-objects from "not_Taken"-list in Firebase
    // filters out objects with the same date as users accepted shifts
    // filters out objects with the same date

    suspend fun getAlertList(userId:String, recentAccepted:MutableList<Varsel>, ansattId: String): MutableList<Varsel> {

        val ref =
            FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("not_Taken")
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


        return list//.filter { !(harVakt(recentAccepted,it.date!!, ansattId)) }
                //.distinctBy { it.date } as MutableList<Varsel>
    }

    //get all varsel-objects from "Taken" list in firebase
    suspend fun getAcceptedShifts(userId:String): MutableList<Varsel> {

        val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("Taken")
        val list = mutableListOf<Varsel>()
        val alertListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (i in dataSnapshot.children) {
                        val acceptedShift = i.getValue(Varsel::class.java)
                        list.add(acceptedShift!!)
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

//moves Varsel-object from "not_Taken"-list to "taken"-list
fun acceptAlert(alert:Varsel, ansattId: String){
    val ref = FirebaseDatabase.getInstance()
            .getReference("Varsler")
            .child(alert.adminId!!)
            .child("not_Taken")
            .child(alert.varselId!!)

    val acceptedAlert = Varsel(varselId= alert.varselId, date = alert.date,tatt = true, ansattId = ansattId, adminId = alert.adminId)

    FirebaseDatabase.getInstance().
    getReference("Varsler").
    child(alert.adminId!!).
    child("Taken").
    child(alert.varselId!!).
    setValue(acceptedAlert)

    ref.removeValue()
}

// check if the user already has a shift that day, in case multiple alerts were sent
fun hasShift(accepted: MutableList<Varsel>, date:String,ansattId:String):Boolean{
    for (i in accepted)
        if (i.date == date && i.ansattId == ansattId) return true
    return false
}





