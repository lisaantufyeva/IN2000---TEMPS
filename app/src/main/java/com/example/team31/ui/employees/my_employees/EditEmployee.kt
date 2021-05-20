package com.example.team31.ui.employees.myEmployees

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.team31.AdminActivity
import com.example.team31.R
import com.example.team31.databinding.FragmentEditEmployeeBinding
import com.example.team31.ui.employees.my_employees.MyEmployeesViewModel


class EditEmployee : Fragment(){

    private lateinit var modelMy: MyEmployeesViewModel
    private val args: EditEmployeeArgs by navArgs()
    private lateinit var mBinding: FragmentEditEmployeeBinding
    private lateinit var userId:String
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentEditEmployeeBinding.inflate(layoutInflater)

        modelMy = ViewModelProvider(this).get(MyEmployeesViewModel::class.java)



        userId = (activity as AdminActivity?)!!.getUserId()
        val ansattId = args.ansattId

        val emailInput = mBinding.etEmailEdit
        val nameInput = mBinding.etNavnEdit
        val rolleInput = mBinding.etRolleEdit



        mBinding.saveButton.setOnClickListener {
            if (emailInput.text.toString().isBlank() || nameInput.text.toString().isBlank() || rolleInput.text.toString().isBlank()) {
                Toast.makeText(activity, "Fyll ut alle feltene", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }
            if(emailInput.text.toString().trim().matches(emailPattern.toRegex())){
            Log.i("ID", ansattId)
            modelMy.editAnsatt(userId, ansattId, emailInput.text.toString(), rolleInput.text.toString(),nameInput.text.toString())

            Navigation.findNavController(it).navigate(R.id.navigation_employees)
            }
            else{
                Toast.makeText(activity, "Emailen er ikke gyldig", Toast.LENGTH_SHORT).show()
                (activity as AdminActivity?)!!.hideKeyboard()
                return@setOnClickListener
            }
        }


        return mBinding.root


    }




}

