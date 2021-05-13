package com.example.team31.ui.ansatt_interface.ansatt_profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.team31.AnsattActivity
import com.example.team31.R
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
        val user=(activity as AnsattActivity?)!!.getUser()
        Log.d("Testansatt",user.navn.toString())

        val root=inflater.inflate(R.layout.profile_fragment_ansatt_fragment,container,false)

        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val navnAnsatt: TextView = root.findViewById(R.id.navnAnsatt)
                navnAnsatt.text="Navn:"+user.navn
                val emailAnsatt:TextView = root.findViewById(R.id.emailAnsatt)
                emailAnsatt.text="Email:"+user.email
            }

        }
        return root
    }
        return inflater.inflate(R.layout.profile_fragment_ansatt_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileFragmentAnsattViewModel::class.java)
        // TODO: Use the ViewModel
    }

}