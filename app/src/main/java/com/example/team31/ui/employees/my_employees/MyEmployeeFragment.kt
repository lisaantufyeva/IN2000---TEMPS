package com.example.team31.ui.employees.my_employees

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.team31.AdminActivity
import com.example.team31.Bruker
import com.example.team31.Ansatt
import com.example.team31.R
import com.example.team31.databinding.EmployeesFragmentBinding

class MyEmployeeFragment : Fragment() {


    private val modelMy: MyEmployeesViewModel by activityViewModels()


    private lateinit var mBinding: EmployeesFragmentBinding
    private var users = mutableListOf<Ansatt>()
    private lateinit var admin:Bruker



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        admin = (activity as AdminActivity?)!!.getUser()
        modelMy.getUsers(admin)
        users = modelMy.getEmployees()
        Log.i("message", users.toString())


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

    //button to add an employee
        mBinding = EmployeesFragmentBinding.inflate(inflater, container, false)
        val leggTIl = mBinding.leggtilknapp

        leggTIl.setOnClickListener{
            findNavController().navigate(R.id.addMyEmpoyeeFragment)
        }

        return mBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // set the layout manager that this RecyclerView will use.
        mBinding.rvAnsattList.layoutManager = GridLayoutManager(requireActivity(), 1)
        // Adapter class is initialized and list is passed in the param.
        Handler().postDelayed({
            val emAdapter = MyEmployeeAdapter(this@MyEmployeeFragment, users)
            mBinding.rvAnsattList.adapter = emAdapter
            emAdapter.notifyDataSetChanged()
        }, 500)


        // END

    }

    fun add(myEmployee: Ansatt) {
        users.add(myEmployee)
    }


}


