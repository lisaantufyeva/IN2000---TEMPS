package com.example.team31.ui.ansatt_interface.available_shifts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team31.Varsel
import com.example.team31.ui.overview.week_overview.RefinedForecast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AvailableShiftsListViewModel : ViewModel() {

    private val _alertList = MutableLiveData<List<Varsel>>()
    val alertList: LiveData<List<Varsel>>
        get() = _alertList

    suspend fun getAlertList(userId:String, recentAccepted:MutableList<Varsel>, ansattId: String): MutableList<Varsel> {

        val ref =
            FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("not_Taken")
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
        //delay(500)
        delay(800)
        //_alertList.value = liste
        //val updatedlist = liste.distinctBy { it.date } as MutableList<Varsel>
        //updatedlist.filter { !harVakt(recentAccepted,it.date!!, userId) }
        println("RECENT ACCEPTED: ")
        println(recentAccepted)
        //return liste.filter { !harVakt(recentAccepted,it.date!!, userId) }.distinctBy { it.date } as MutableList<Varsel>

        return liste.filter { !(harVakt(recentAccepted,it.date!!, ansattId)) }.distinctBy { it.date } as MutableList<Varsel>
    }

    suspend fun getAcceptedShifts(userId:String): MutableList<Varsel> {

        val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("Taken")
        val liste = mutableListOf<Varsel>()
        val alertListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (i in dataSnapshot.children) {
                        val varsel = i.getValue(Varsel::class.java)
                        liste.add(varsel!!)
                        Log.i("ansatt hentes:", varsel.toString())
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("message", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(alertListener)
        //delay(500)
        delay(800)
        //_alertList.value = liste
        //return liste.distinctBy { it.date } as MutableList<Varsel>
        return liste
    }
}

//moves Varsel-object from "not_Taken"-list to "taken"-list
fun acceptAlert(alert:Varsel, ansattId: String){
    val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(alert.adminId!!).child("not_Taken").child(alert.varselId!!)
    val acceptedAlert = Varsel(varselId= alert.varselId, date = alert.date,tatt = true, ansattId = ansattId, adminId = alert.adminId)
    FirebaseDatabase.getInstance().getReference("Varsler").child(alert.adminId!!).child("Taken").child(alert.varselId!!).setValue(acceptedAlert)
    ref.removeValue()
}

// check if "vakten er tatt"- label is visible
fun harVakt(accepted: MutableList<Varsel>, date:String,ansattId:String):Boolean{
    for (i in accepted)
        if (i.date == date && i.ansattId == ansattId) return true
    return false
}





