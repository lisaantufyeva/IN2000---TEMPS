package com.example.team31.ui.overview.detail

import android.util.Log
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.team31.Bruker
import com.example.team31.Varsel
import com.example.team31.ui.employees.Ansatt
import com.example.team31.ui.overview.week_overview.RefinedForecast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select

class DetailViewModel(
) : ViewModel() {

    fun update_alertList(alertList: MutableList<Varsel>, userId:String){
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("varselListe", alertList)

        ref.updateChildren(hashMap)

    }

    fun addAlert(alert:Varsel){
        val ref = FirebaseDatabase.getInstance().getReference("Varsler").child(alert.adminId!!).child("not_Taken")
        alert.varselId = ref.push().key
        ref.child(alert.varselId!!).setValue(alert).addOnCompleteListener {
            //Toast.makeText(this, "Bruker registrert", Toast.LENGTH_SHORT).show()
            Log.d("Firebase","Varsel saved")
        }
    }
/*
    suspend fun getAlertList(userId:String): MutableList<Varsel> {
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("varselListe")
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
    }*/

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