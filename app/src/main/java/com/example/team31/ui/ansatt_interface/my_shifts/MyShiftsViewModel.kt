package com.example.team31.ui.ansatt_interface.my_shifts

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.team31.Varsel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay

class MyShiftsViewModel : ViewModel() {
    suspend fun getMyShiftList(adminId:String, userId:String): MutableList<Varsel> {

        val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(adminId).child("Taken")
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
        return liste.filter { it.ansattId == userId  } as MutableList<Varsel>
    }
}