package com.example.team31

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class Registrering : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrering)

        val knapp = findViewById<Button>(R.id.registrer_knapp)

        knapp.setOnClickListener {
            val passord1 = findViewById<TextView>(R.id.passord1)
            val passord2 = findViewById<TextView>(R.id.passord2)
            val email = findViewById<TextView>(R.id.email)
            val name = findViewById<TextView>(R.id.name)

            if (passord1.text.toString().isBlank() || email.text.toString().isBlank() || passord2.text.toString().isBlank() || name.text.toString().isBlank()){
                Toast.makeText(this, "Fyll ut alle feltene", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                return@setOnClickListener
            }

            if (passord1.text.toString() == passord2.text.toString()){
                val emailSvar = email.text.toString()
                val passordSvar = passord1.text.toString()
                val navnSvar = name.text.toString()
                //val user = Bruker(6, emailSvar, passordSvar, null, navnSvar)
                saveUser(navnSvar, emailSvar, passordSvar)
                email.text = ""
                passord1.text = ""
                passord2.text = ""
                name.text = ""
                //liste.add(user)
                hideKeyboard()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Passordene er ikke like", Toast.LENGTH_SHORT).show()
                email.text = ""
                passord1.text = ""
                passord2.text = ""
                name.text = ""
                hideKeyboard()
            }
        }

    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null){
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)


        }
    }
    private fun saveUser(navn :String, email :String, passord :String){
        val ref = FirebaseDatabase.getInstance().getReference("Users")

        val userId = ref.push().key

        val user = Bruker(userId, email, passord, null, navn)

        ref.child(userId!!).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Bruker registrert", Toast.LENGTH_SHORT).show()
        }

    }
}