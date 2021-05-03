package com.example.team31.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.team31.Authentication
import com.example.team31.Bruker
import com.example.team31.R

class registration_name_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.registration_name_fragment, container, false)

        val knapp = root.findViewById<Button>(R.id.registrer_knapp)

        knapp.setOnClickListener{
            val passord1 = root.findViewById<TextView>(R.id.passord1)
            val passord2 = root.findViewById<TextView>(R.id.passord2)
            val email = root.findViewById<TextView>(R.id.email)
            val name = root.findViewById<TextView>(R.id.name)

            if (passord1.text.toString().isBlank() || email.text.toString().isBlank() || passord2.text.toString().isBlank() || name.text.toString().isBlank()){
                Toast.makeText(activity, "Fyll ut alle feltene", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }

            if (passord1.text.toString() == passord2.text.toString()){
                val emailSvar = email.text.toString()
                val passordSvar = passord1.text.toString()
                val navnSvar = name.text.toString()
                val newUser: Bruker = Bruker(null, emailSvar,passordSvar,null,navnSvar,null,null,null,null,null,null,null)
                email.text = ""
                passord1.text = ""
                passord2.text = ""
                name.text = ""
                (activity as Authentication?)!!.hideKeyboard()
                val action = registration_name_fragmentDirections.actionRegistrationNameFragmentToLocationFragment(newUser)
                Navigation.findNavController(root).navigate(action)
            }
            else{
                Toast.makeText(activity, "Passordene er ikke like", Toast.LENGTH_SHORT).show()
                email.text = ""
                passord1.text = ""
                passord2.text = ""
                name.text = ""
                (activity as Authentication?)!!.hideKeyboard()
            }
        }


        return root
    }
}