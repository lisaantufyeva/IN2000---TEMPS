package com.example.team31.ui.ansatt_interface.ansatt_profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.team31.R

class ProfileFragmentAnsatt : Fragment() {

    companion object {
        fun newInstance() = ProfileFragmentAnsatt()
    }

    private lateinit var viewModel: ProfileFragmentAnsattViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment_ansatt_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileFragmentAnsattViewModel::class.java)
        // TODO: Use the ViewModel
    }

}