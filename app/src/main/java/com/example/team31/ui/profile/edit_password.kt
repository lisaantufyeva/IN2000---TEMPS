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

class edit_password : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root =  inflater.inflate(R.layout.edit_password, container, false)
        val user = (activity as AdminActivity?)!!.getUser()

        val save = root.findViewById<Button>(R.id.save)
        val old= root.findViewById<TextView>(R.id.password_old)
        val new1 = root.findViewById<TextView>(R.id.new_password1)
        val new2 = root.findViewById<TextView>(R.id.new_password2)

        save.setOnClickListener {
            val oldPass = old.text.toString()
            val newPass1 = new1.text.toString()
            val newPass2 = new2.text.toString()
            if (oldPass.isBlank() || newPass1.isBlank() || newPass2.isBlank()) {
                Toast.makeText(activity, "Ugyldig input!", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

            if(oldPass == user.passord){
                if(checkPassword(newPass1)){
                if(newPass1 == newPass2){
                    profileViewModel.update_password(newPass1,user.id!!)
                    Navigation.findNavController(root).navigate(R.id.action_edit_password_to_navigation_profile)
                }
                else{
                    Toast.makeText(activity, "De nye passordene er ikke like", Toast.LENGTH_SHORT).show()
                    (activity as AdminActivity?)!!.hideKeyboard()
                    return@setOnClickListener
                    }
                }
                else{
                    Toast.makeText(activity, "Nytt passord oppfyller ikke kravene. Må ha ulike tegn og inneholde minimum 6 bokstaver", Toast.LENGTH_SHORT).show()
                    (activity as AdminActivity?)!!.hideKeyboard()
                    return@setOnClickListener
                }
            }
            else{
                Toast.makeText(activity, "Matcher ikke ditt gamle passord. Prøv igjen", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

        }
        return root
    }

    fun checkPassword(password:String):Boolean{
        for (i in 0..password.length-1) {
            if(password[i] != password[0]){
                if(password.length > 5){
                    return true
                }
            }
        }
        return false
    }

}