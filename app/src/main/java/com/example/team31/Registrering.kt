package com.example.team31

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.firebase.database.FirebaseDatabase
/*
class Registrering : AppCompatActivity() {

    lateinit var stedNavn: String
    lateinit var latlng:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrering)

        val knapp = findViewById<Button>(R.id.registrer_knapp)

        // Searchbar
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(),"AIzaSyDwU7qloh3FrztFPxnyR4zbbKOBUGmgN9Y")
        }

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(this)



        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Log.i("Svar", "Place: ${place.name}, ${place.latLng}")

                stedNavn = place.name.toString()
                latlng = place.latLng.toString()



            }

            override fun onError(p0: Status) {
                Log.i("FEIL", p0.status.toString())
            }
        })





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
                saveUser(navnSvar, emailSvar, passordSvar, latlng, stedNavn)
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
    private fun saveUser(navn :String, email :String, passord :String, latlng:String, stedNavn:String){
        val ref = FirebaseDatabase.getInstance().getReference("Users")

        val userId = ref.push().key

        val user = Bruker(userId, email, passord, null, navn, latlng, stedNavn)

        ref.child(userId!!).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Bruker registrert", Toast.LENGTH_SHORT).show()
        }

    }
}*/