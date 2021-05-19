package com.example.team31.ui.ansatt_interface.ansatt_profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.team31.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragmentAnsatt : Fragment() {

    companion object {
        fun newInstance() = ProfileFragmentAnsatt()
    }

    private lateinit var viewModel: ProfileFragmentAnsattViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val user = (activity as AnsattActivity?)!!.getUser()
        Log.d("Testansatt",user.navn.toString())

        val root=inflater.inflate(R.layout.profile_fragment_ansatt_fragment,container,false)

        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val navnAnsatt: TextView = root.findViewById(R.id.navnAnsatt)
                navnAnsatt.text="Navn: "+user.navn
                val emailAnsatt:TextView = root.findViewById(R.id.emailAnsatt)
                emailAnsatt.text="Email: "+user.email
                val rolleAnsatt:TextView = root.findViewById(R.id.rolleAnsatt)
                rolleAnsatt.text="Rolle: "+user.rolle
                (activity as AnsattActivity?)!!.updateUser(user)
            }

        }

        val edit_employee_password_temp = root.findViewById<Button>(R.id.edit_employee_password_button)
        edit_employee_password_temp.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_profile_ansatt_to_edit_employee_password)
        }

        val logOut = root.findViewById<Button>(R.id.logg_ut)
        logOut.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        return root
    }
       // return inflater.inflate(R.layout.profile_fragment_ansatt_fragment, container, false)


    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileFragmentAnsattViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

}