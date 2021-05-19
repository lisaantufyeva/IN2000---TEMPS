package com.example.team31.authentication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.team31.Authentication
import com.example.team31.R
import com.google.android.material.button.MaterialButton


class login_employee_fragment : Fragment() {

    private val model: AuthenticationViewModel by activityViewModels()

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //model.getAnsatte()
        println(model.getAnsatteList())


        val root =  inflater.inflate(R.layout.login_employee_fragment, container, false)
        val loginKnapp = root.findViewById<Button>(R.id.loginn_knapp)
        val e1= root.findViewById<TextView>(R.id.email)
        val p1 = root.findViewById<TextView>(R.id.passord)
        val switchtoAdmin = root.findViewById<SwitchCompat>(R.id.switchtoAdmin)

        switchtoAdmin.setOnClickListener{
            if (!switchtoAdmin.isChecked)
                Navigation.findNavController(root).navigate(R.id.action_login_employee_fragment_to_login_fragment)
        }

        loginKnapp.setOnClickListener {
            val email = e1.text.toString()
            val passord = p1.text.toString()
            if (email.isBlank() || passord.isBlank() ){
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as Authentication?)!!.hideKeyboard()
                return@setOnClickListener
            }

            val ansatt = model.loginAnsatt(email,passord)
            if(ansatt != null){
                Log.i("bruker:", ansatt.toString())
                e1.text = ""
                p1.text = ""
                (activity as Authentication?)!!.startAnsattActivity(ansatt)
            }
            else{
                Toast.makeText(activity, "Feil ved innlogging. Ta kontakt med arbeidsgiveren.", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
}

