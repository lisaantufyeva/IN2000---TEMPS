package com.example.team31.ui.ansatt_interface.ansatt_profile

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
import com.example.team31.AnsattActivity
import com.example.team31.Authentication
import com.example.team31.R

private lateinit var viewModel: ProfileFragmentAnsattViewModel

class edit_employee_password : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(ProfileFragmentAnsattViewModel::class.java)
        val root =  inflater.inflate(R.layout.edit_employee_password, container, false)
        val user = (activity as AnsattActivity?)!!.getUser()
        //val userId = (activity as AnsattActivity?)!!.getUserId()

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
                (activity as AnsattActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

            if(oldPass == user.passord){
                if(newPass1 == newPass2){
                    viewModel.update_password(newPass1,user.adminId!!, user.ansattId!!)
                    Navigation.findNavController(root).navigate(R.id.action_edit_employee_password_to_navigation_profile_ansatt)
                }
                else{
                    Toast.makeText(activity, "De nye passordene er ikke like", Toast.LENGTH_SHORT).show()
                    (activity as AnsattActivity?)!!.hideKeyboard()
                    return@setOnClickListener
                }
            }
            else{
                Toast.makeText(activity, "Matcher ikke ditt gamle passord. Prøv igjen", Toast.LENGTH_SHORT).show()
                (activity as AnsattActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }

        }
        return root
    }
}