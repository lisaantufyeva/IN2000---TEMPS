package com.example.team31.authentication

import android.os.Bundle
import android.provider.VoicemailContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.team31.Bruker
import com.example.team31.R
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.gms.common.api.Status

class location_fragment : Fragment() {

    lateinit var stedNavn: String
    lateinit var latlng:String
    lateinit var user:Bruker

    private val args by navArgs<location_fragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.location_fragment, container, false)
        val knapp = root.findViewById<Button>(R.id.registrer_knapp)
        user = args.user

        if (!Places.isInitialized()) {
            val x = activity
            if (x!=null) {
                Places.initialize(x.getApplicationContext(), "AIzaSyDwU7qloh3FrztFPxnyR4zbbKOBUGmgN9Y")
            }
        }

        // Create a new PlacesClient instance
        //val placesClient = Places.createClient(FragmentActivity)



        // Initialize the AutocompleteSupportFragment.
        //val s = root.findFragmentById(R.id.autocomplete_fragment)

        val autocompleteFragment = childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

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
        knapp.setOnClickListener{
            if(stedNavn == "" || latlng == ""){
                Log.i("FEIL", "Kunne ikke oppdatere bruker")
                return@setOnClickListener
            }

            user.stedNavn = stedNavn
            user.latlng = latlng

            val action = location_fragmentDirections.actionLocationFragmentToBemanningFragment(user)
            Navigation.findNavController(root).navigate(action)
            //Navigation.findNavController(root).navigate(R.id.action_location_fragment_to_bemanning_fragment)
        }


        return root
    }
}