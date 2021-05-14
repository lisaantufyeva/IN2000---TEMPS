package com.example.team31.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.team31.Bruker
import com.example.team31.Varsel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*

class ProfileViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Profile fragment"
    }

    fun updateName_Email(email:String,name:String,userId: String){
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("email",email)
        hashMap.put("navn", name)

        ref.updateChildren(hashMap)

    }

    fun updatePos(latitude:String, longitude: String, location_name:String, userId: String){
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("latitude",latitude)
        hashMap.put("longitude", longitude)
        hashMap.put("stedNavn",location_name)

        ref.updateChildren(hashMap)
    }

    fun update_work(normal:String,max:String,userId: String){
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("normalBemanning",normal)
        hashMap.put("maxBemanning", max)

        ref.updateChildren(hashMap)

    }

    fun update_weather(trigger:String,nedbor:Boolean,userId: String){
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("triggerTemp",trigger)
        hashMap.put("nedbor", nedbor)

        ref.updateChildren(hashMap)
    }

    fun update_opening(open:String, close:String, userId: String){
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("aapenFra",open)
        hashMap.put("aapenTil", close)

        ref.updateChildren(hashMap)
    }

    fun update_password(password:String, userId: String){
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("passord",password)

        ref.updateChildren(hashMap)
    }

    fun update_list(liste:MutableList<Varsel>, userId: String){
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(userId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("varselListe",liste)

        ref.updateChildren(hashMap)
    }



    suspend fun getUser(userId:String):Bruker{
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val brukere = ArrayList<Bruker>()
        var mainUser = Bruker()

        // Henter brukere fra firebase
            val UserListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (i in dataSnapshot.children) {
                            val user = i.getValue(Bruker::class.java)
                            brukere.add(user!!)

                            if (user.id == userId) {
                                mainUser = user
                            }
                            //Log.i("bruker", mainUser.toString())
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w("message", "loadPost:onCancelled", databaseError.toException())
                }
            }
        ref.addValueEventListener(UserListener)
        delay(500)
        return mainUser
    }

}