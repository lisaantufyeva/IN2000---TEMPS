package com.example.team31.ui.employees.myEmployees

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.team31.R
import com.example.team31.databinding.AddmyemployeeFragmentBinding
import com.example.team31.databinding.FragmentEditEmployeeBinding

class editEmployee : Fragment(), View.OnClickListener {

    private lateinit var mBinding: FragmentEditEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentEditEmployeeBinding.inflate(layoutInflater)

        mBinding.saveButton.setOnClickListener(this)
        return mBinding.root


    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {

                R.id.save ->{
                    findNavController().navigate(
                        R.id.navigation_employees)
                }



}}}





}