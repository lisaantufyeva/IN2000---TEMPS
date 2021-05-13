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

    suspend fun getAlertList(userId:String): MutableList<Varsel> {

        val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(userId).child("not_Taken")
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
        delay(200)
        //_alertList.value = liste
        return liste.distinctBy { it.date } as MutableList<Varsel>
    }
}