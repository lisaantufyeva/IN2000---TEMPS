package com.example.team31

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val knapp = findViewById<Button>(R.id.loginn_knapp)
        val registrerKnapp = findViewById<Button>(R.id.registrer_knapp)

        //val bruker1 = Bruker("1", "gregoraskjer1998@gmail.com", "team31",  "https://scontent.fosl4-2.fna.fbcdn.net/v/t1.0-9/20914600_1599415190131918_6973241602838320977_n.jpg?_nc_cat=109&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=u40y80CFTb4AX9ym_wJ&_nc_ht=scontent.fosl4-2.fna&oh=5050d239d40b315210e726be23efd012&oe=6080875B", "Gregor Askjer")
        //val bruker2 = Bruker("2", "erna@gmail.com", "team31", "https://media.snl.no/media/55190/standard_ernasolberg2_portrett.jpg", "Erna Solberg")
        //val bruker3 = Bruker("3", "jonas@gmail.com", "team31", "https://scontent.fosl4-2.fna.fbcdn.net/v/t1.0-9/25299036_1651451734877105_8386288601747239610_n.jpg?_nc_cat=101&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=GZX3gMALj_wAX9454mm&_nc_ht=scontent.fosl4-2.fna&oh=6b87999d39614bb4a068c0628f8f0c36&oe=607F6F4F", "Jonas Erikstein")
        //val bruker4 = Bruker("4", "henrik@gmail.com", "team31", "https://media-exp1.licdn.com/dms/image/C5603AQErhUyvuP4IFg/profile-displayphoto-shrink_200_200/0/1517526098923?e=1618444800&v=beta&t=gR3iaoZIg8ZJnx-xXfrnmvwyiUtutQoBnCUzsh0bsdA", "Henrik Baardson")

        val brukere = mutableListOf<Bruker>()
        //brukere.add(bruker1)
       // brukere.add(bruker2)
       //brukere.add(bruker3)
        //brukere.add(bruker4)
        val ref = FirebaseDatabase.getInstance().getReference("Users")

        // Henter brukere fra firebase
        val UserListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    for(i in dataSnapshot.children){
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
        ref.addValueEventListener(UserListener)



        val email = findViewById<TextView>(R.id.email)
        val passord = findViewById<TextView>(R.id.passord)

        knapp.setOnClickListener {
            val email_svar = email.text.toString()
            val passord_svar = passord.text.toString()
            if (email_svar.isBlank() || passord_svar.isBlank() ){
                Toast.makeText(this, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                return@setOnClickListener
            }
            //val user = brukere.email.find(email_svar)
            val user: Bruker? = brukere.find { it.email == email_svar }

            if (user != null) {
                //Toast.makeText(this@MainActivity, user.navn, Toast.LENGTH_SHORT).show()

                if (passord_svar == user.passord){
                    startActivity2(user)
                }
                else {
                    email.text = ""
                    passord.text = ""
                    Toast.makeText(this, "Feil passord", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Du er ikke registrert hos oss. Registrer deg nå!", Toast.LENGTH_LONG).show()
            }
            hideKeyboard()
        }

        registrerKnapp.setOnClickListener {
            startActivity3()
        }

    }
    // starter Infoskjerm
    /*
    fun startActivity2(i:Bruker){
        val intent = Intent(this, InfoSkjerm::class.java)
        intent.putExtra("User", i as Serializable)
        startActivity(intent)
    }*/

    //Starter AdminActivity
    fun startActivity2(i:Bruker){
        val intent = Intent(applicationContext, AdminActivity::class.java)
        intent.putExtra("User", i as Serializable)
        startActivity(intent)
    }

    fun startActivity3(){
        val intent = Intent(this, Registrering::class.java)
        startActivity(intent)
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null){
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)


        }
    }
}