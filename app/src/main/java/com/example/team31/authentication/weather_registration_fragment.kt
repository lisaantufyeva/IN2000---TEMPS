package com.example.team31.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.team31.Authentication
import com.example.team31.Bruker
import com.example.team31.R

class weather_registration_fragment : Fragment() {
    lateinit var user: Bruker

    private val args  by navArgs<weather_registration_fragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        user = args.user
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.weather_registration_fragment, container, false)

        val trigger = root.findViewById<EditText>(R.id.trigger_temp)

        val box = root.findViewById<CheckBox>(R.id.checkBox1)

        val knapp = root.findViewById<Button>(R.id.save)
        knapp.setOnClickListener{
            if (trigger.text.isBlank()){
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }

            if(box.isChecked){
                user.nedbor = true
            }

            user.triggerTemp = trigger.text.toString()
            if (user.triggerTemp!!.toInt() > 60){
                Toast.makeText(activity, " Ugyldig triggertemp. Max temp målt på jorda er 58!", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }


            val action = weather_registration_fragmentDirections.actionWeatherRegistrationFragmentToAapningstiderFragment(user)
            Navigation.findNavController(root).navigate(action)
        }

        return root
    }

}