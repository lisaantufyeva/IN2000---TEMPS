package com.example.team31.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.team31.AdminActivity
import com.example.team31.Authentication
import com.example.team31.R

private lateinit var profileViewModel: ProfileViewModel
private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

class edit_name : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.edit_name, container, false)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val userId = (activity as AdminActivity?)!!.getUserId()

        val knapp = root.findViewById<Button>(R.id.lagre)
        val email = root.findViewById<TextView>(R.id.email)
        val name = root.findViewById<TextView>(R.id.name)

        knapp.setOnClickListener {
            if (email.text.toString().isBlank() || name.text.toString().isBlank()) {
                Toast.makeText(activity, "Fyll ut alle feltene", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }
            if(email.text.toString().trim().matches(emailPattern.toRegex())) {
                profileViewModel.updateName_Email(email.text.toString(), name.text.toString(), userId)

                Navigation.findNavController(root).navigate(R.id.action_edit_name_to_navigation_profile)
            }
            else{
                Toast.makeText(activity, "Emailen er ikke gyldig", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

        }
        return root
    }
}