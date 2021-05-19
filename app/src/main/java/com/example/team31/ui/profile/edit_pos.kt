package com.example.team31.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.team31.AdminActivity
import com.example.team31.R
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.gms.common.api.Status


class edit_pos : Fragment() {

    private var stedNavn: String = ""
    private var latitude: String = ""
    private var longitude: String = ""
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val userId = (activity as AdminActivity?)!!.getUserId()
        val root = inflater.inflate(R.layout.edit_pos, container, false)
        val knapp = root.findViewById<Button>(R.id.registrer_knapp)

        if (!Places.isInitialized()) {
            val x = activity
            if (x != null) {
                Places.initialize(x.getApplicationContext(), "AIzaSyDwU7qloh3FrztFPxnyR4zbbKOBUGmgN9Y")
            }
        }

        // Create a new PlacesClient instance
        //val placesClient = Places.createClient(FragmentActivity)


        // Initialize the AutocompleteSupportFragment.
        //val s = root.findFragmentById(R.id.autocomplete_fragment)

        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG
            )
        )

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.i("Svar", "Place: ${place.name}, ${place.latLng}")

                val location = place.latLng

                if (location != null) {
                    latitude = location.latitude.toString()
                    longitude = location.longitude.toString()
                }

                stedNavn = place.name.toString()


            }

            override fun onError(p0: Status) {
                Log.i("FEIL", p0.status.toString())
            }
        })
        knapp.setOnClickListener {
            if (stedNavn == "" || latitude == "") {
                Log.i("FEIL", "Kunne ikke oppdatere bruker")
                Toast.makeText(activity, "Velg nytt sted", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            profileViewModel.updatePos(latitude,longitude,stedNavn,userId)
            Navigation.findNavController(root).navigate(R.id.action_edit_pos_to_navigation_profile)
        }
        return root
    }
}