package com.example.team31.ui.employees.myEmployees

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.compose.navArgument
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.team31.AdminActivity
import com.example.team31.R
import com.example.team31.authentication.location_fragmentArgs
import com.example.team31.databinding.AddmyemployeeFragmentBinding
import com.example.team31.databinding.FragmentEditEmployeeBinding



class editEmployee : Fragment(), View.OnClickListener {

    private val modelMy: MyEmployeesViewModel by activityViewModels()

    private lateinit var mBinding: FragmentEditEmployeeBinding
    private lateinit var userId:String
    private val args by navArgs<editEmployeeArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentEditEmployeeBinding.inflate(layoutInflater)


        userId = (activity as AdminActivity?)!!.getUserId()
        val ansattId = args.ansattId



        mBinding.saveButton.setOnClickListener {
            Log.i("ID", ansattId)
        }


        return mBinding.root


    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {

                R.id.save ->{
                    //modelMy.edit_ansatt()
                    findNavController().navigate(R.id.navigation_employees)
                }


            }
        }
    }





}