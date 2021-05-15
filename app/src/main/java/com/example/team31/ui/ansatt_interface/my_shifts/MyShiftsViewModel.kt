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
        val list = mutableListOf<Varsel>()
        val alertListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (i in dataSnapshot.children) {
                        val shift = i.getValue(Varsel::class.java)
                        list.add(shift!!)
                        Log.i("ansatt hentes:", shift.toString())
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("message", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(alertListener)
        delay(800)
        return list.filter { it.ansattId == userId  } as MutableList<Varsel>
    }
}