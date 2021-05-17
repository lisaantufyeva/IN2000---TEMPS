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

class edit_work : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val userId = (activity as AdminActivity?)!!.getUserId()
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.edit_work, container, false)

        val normal = root.findViewById<EditText>(R.id.normal_bemanning)

        val max = root.findViewById<EditText>(R.id.max_bemanning)

        val knapp = root.findViewById<Button>(R.id.save)

        knapp.setOnClickListener {
            if (normal.text.isBlank() || max.text.isBlank()) {
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }
            val normalBemanning = normal.text.toString()
            val maxBemanning = max.text.toString()
            //Added check to ensure maxBemanning greater than normalBemanning
            if (normalBemanning.toInt() > maxBemanning.toInt()) {
                Toast.makeText(activity, "Ugyldig input! Krav: Max > Normal!!", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

            profileViewModel.update_work(normalBemanning,maxBemanning,userId)

            Navigation.findNavController(root).navigate(R.id.action_edit_work_to_navigation_profile)
        }
        return root
    }
}