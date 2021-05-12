package com.example.team31.ui.overview.detail

import android.util.Log
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.team31.Bruker
import com.example.team31.Varsel
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
    /*
    suspend fun getAlertList(userId:String): MutableList<Varsel> {
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val alertListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val alert = dataSnapshot.getValue()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("message", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(alertListener)
        delay(200)
    }*/


}