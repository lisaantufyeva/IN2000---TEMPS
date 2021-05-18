package com.example.team31.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.team31.AdminActivity
import com.example.team31.Authentication
import com.example.team31.R

class edit_weather : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val userId = (activity as AdminActivity?)!!.getUserId()

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.edit_weather, container, false)

        val trigger = root.findViewById<EditText>(R.id.trigger_temp)

        val box = root.findViewById<CheckBox>(R.id.checkBox1)

        var nedbor = false

        val knapp = root.findViewById<Button>(R.id.save)

        knapp.setOnClickListener {
            if (trigger.text.isBlank()) {
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

            if (box.isChecked) {
                nedbor = true
            }

            val triggerTemp = trigger.text.toString()

            if (triggerTemp.toInt() > 60){
                Toast.makeText(activity, " Ugyldig triggertemp. Max temp målt på jorda er 58!", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

            profileViewModel.update_weather(triggerTemp,nedbor,userId)

            Navigation.findNavController(root).navigate(R.id.action_edit_weather_to_navigation_profile)
        }

        return root
    }
}