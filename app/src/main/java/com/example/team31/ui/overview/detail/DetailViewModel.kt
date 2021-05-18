package com.example.team31.ui.overview.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.team31.Varsel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay

class DetailViewModel(
) : ViewModel() {

    fun addAlert(alert:Varsel){
        val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(alert.adminId!!).child("not_Taken")
        alert.varselId = ref.push().key
        ref.child(alert.varselId!!).setValue(alert).addOnCompleteListener {
            Log.d("Firebase","Varsel saved")
        }
    }


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
        return liste
    }
}