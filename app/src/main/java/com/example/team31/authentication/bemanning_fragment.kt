package com.example.team31.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.team31.AdminActivity
import com.example.team31.Authentication
import com.example.team31.Bruker
import com.example.team31.R

class bemanning_fragment : Fragment() {

    lateinit var user: Bruker

    private val args  by navArgs<bemanning_fragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = args.user
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.bemanning_fragment, container, false)

        val normal = root.findViewById<EditText>(R.id.normal_bemanning)

        val max = root.findViewById<EditText>(R.id.max_bemanning)

        val knapp = root.findViewById<Button>(R.id.save)

        knapp.setOnClickListener{
            if (normal.text.isBlank() || max.text.isBlank() ){
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }
            user.normalBemanning = normal.text.toString()
            user.maxBemanning = max.text.toString()

            //Added check to ensure maxBemanning greater than normalBemanning
            if (user.normalBemanning!!.toInt() > user.maxBemanning!!.toInt()) {
                Toast.makeText(activity, "Ugyldig input! Krav: Max > Normal!!", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }

            val action = bemanning_fragmentDirections.actionBemanningFragmentToWeatherRegistrationFragment(user)
            Navigation.findNavController(root).navigate(action)
        }

        return root
    }

}