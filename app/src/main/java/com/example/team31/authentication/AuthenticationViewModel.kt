package com.example.team31.authentication

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.team31.Bruker
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AuthenticationViewModel : ViewModel(){
    //private var users: MutableLiveData<List<Bruker>> = MutableLiveData<List<Bruker>>()
    private var users = mutableListOf<Bruker>()
    val ref = FirebaseDatabase.getInstance().getReference("Users")



    fun login(email:String,password:String) : Bruker? {
        //val user = brukere.email.find(email_svar)
        val user: Bruker? = users.find { it.email == email }

        if (user != null) {
            //Toast.makeText(this@MainActivity, user.navn, Toast.LENGTH_SHORT).show()

            if (password == user.passord){
                return user
            }
        }
        return null
    }

    fun getUsers() {
        val brukere = ArrayList<Bruker>()

        // Henter brukere fra firebase
        val UserListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (i in dataSnapshot.children) {
                        val user = i.getValue(Bruker::class.java)
                        brukere.add(user!!)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("message", "loadPost:onCancelled", databaseError.toException())
            }
        }
        users = brukere
        ref.addValueEventListener(UserListener)
    }

    fun saveUser(user:Bruker){

        val userId = ref.push().key
        user.id = userId
        val mail = user.email

        if (mail != null){
            if(users.find {it.email == mail}!=null){
                Log.i("message", "Brukeren finnes fra før")
                return
            }
        }

        ref.child(userId!!).setValue(user).addOnCompleteListener {
            //Toast.makeText(this, "Bruker registrert", Toast.LENGTH_SHORT).show()
            Log.d("Firebase","User saved")
        }
    }

}