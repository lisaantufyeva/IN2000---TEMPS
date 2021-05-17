package com.example.team31.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.team31.AdminActivity
import com.example.team31.Authentication
import com.example.team31.R

private lateinit var profileViewModel: ProfileViewModel

class edit_opening : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.edit_opening, container, false)
        val userId = (activity as AdminActivity?)!!.getUserId()

        val start = root.findViewById<EditText>(R.id.start)
        val slutt = root.findViewById<EditText>(R.id.slutt)

        val knapp = root.findViewById<Button>(R.id.save)
        knapp.setOnClickListener {
            if (start.text.isBlank() || slutt.text.isBlank()) {
                Toast.makeText(activity, "Ugyldig input! Fyll ut begge feltene", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }
            val aapenFra = start.text.toString()
            val aapenTil = slutt.text.toString()

            if (aapenFra.toInt() < 0 || aapenFra.toInt() > 24){
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }
            if (aapenTil.toInt() < 0 || aapenTil.toInt() > 24){
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }
            if (aapenFra.toInt() > aapenTil.toInt()){
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

            profileViewModel.update_opening(aapenFra,aapenTil,userId)

            Navigation.findNavController(root).navigate(R.id.action_edit_opening_to_navigation_profile)
        }
        return root
    }
}