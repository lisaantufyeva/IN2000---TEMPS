package com.example.team31.authentication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.team31.AdminActivity
import com.example.team31.Authentication
import com.example.team31.MainActivity
import com.example.team31.R


class login_fragment : Fragment() {

    private val model: AuthenticationViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        model.getUsers()
        val root =  inflater.inflate(R.layout.login_fragment, container, false)
        val regKnapp = root.findViewById<Button>(R.id.registrer_knapp)
        val loginKnapp = root.findViewById<Button>(R.id.loginn_knapp)
        val e= root.findViewById<TextView>(R.id.email)
        val p = root.findViewById<TextView>(R.id.passord)

        regKnapp.setOnClickListener{
            Navigation.findNavController(root).navigate(R.id.action_login_fragment_to_registration_name_fragment)
        }

        loginKnapp.setOnClickListener {
            val email = e.text.toString()
            val passord = p.text.toString()
            if (email.isBlank() || passord.isBlank() ){
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }

            val user = model.login(email,passord)

            if(user != null){
                Log.i("bruker:", user.toString())
                (activity as Authentication?)!!.startActivity2(user)
            }
            else{
                Toast.makeText(activity, "Feil ved innlogging. Registrer deg eller skriv rett passord.", Toast.LENGTH_SHORT).show()
            }
        }



        return root
    }

}

