package com.example.team31.ui.employees

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.team31.AdminActivity
import com.example.team31.Bruker
import com.example.team31.R
import com.example.team31.authentication.AuthenticationViewModel
import com.example.team31.databinding.EmployeesFragmentBinding
import com.google.android.play.core.internal.i
import kotlinx.coroutines.coroutineScope
import java.io.Serializable

class EmployeesFragment : Fragment() {
/*
    companion object {
        fun newInstance() = OverviewFragment()
    }*/

    //private lateinit var employeesViewModel: EmployeesViewModel

    private val model: EmployeesViewModel by activityViewModels()


    private lateinit var mBinding: EmployeesFragmentBinding
    private var users = mutableListOf<Ansatt>()
    private lateinit var admin:Bruker

    //val henrik = Ansatt("henrik", "henrik@gmail.com", "Serviør")
    //val lisz = Ansatt("Liza", "Liza@gmail.com", "Vaktsjef")
    //val greogor = Ansatt("Gregor", "gregor@gmail.com", "Vaktsjef")
    //val katerina = Ansatt("Katerina", "kat@gmail.com", "Vaktsjef")
    //val dragana = Ansatt("dragana", "dragana@email.com", "Kokk")





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        admin = (activity as AdminActivity?)!!.getUser()
        model.getUsers(admin)
        users = model.getEmployees()
        Log.i("message",users.toString())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //admin = (activity as AdminActivity?)!!.getUser()
        //model.getUsers(admin)
        //users = model.getEmployees()
       // Log.i("message",users.toString())
//        employeesViewModel =
//            ViewModelProvider(this).get(EmployeesViewModel::class.java)
//        val root = inflater.inflate(R.layout.employees_fragment, container, false)
//        val textView: TextView = root.findViewById(R.id.text_employees)
//
//        employeesViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        mBinding = EmployeesFragmentBinding.inflate(inflater, container, false)
        return mBinding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //add(dragana)
        //add(greogor)
        //add(lisz)
        //add(henrik)

        // Set the LayoutManager that this RecyclerView will use.
        mBinding.rvAnsattList.layoutManager = GridLayoutManager(requireActivity(), 1)
        // Adapter class is initialized and list is passed in the param.
        Handler().postDelayed({
            val emAdapter = EmployeeAdapter(this@EmployeesFragment, users)
            mBinding.rvAnsattList.adapter = emAdapter
            emAdapter.notifyDataSetChanged()
        }, 500)







        // END

    }

    fun add(ansatt : Ansatt){
        users.add(ansatt)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_employee, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_add_employee -> {
                //startActivity(Intent(requireActivity(), LeggTilRedigerAnsatt::class.java))
                startActivity1(admin)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun startActivity1(user:Bruker){
        val intent = Intent(requireContext(), LeggTilRedigerAnsatt::class.java)
        intent.putExtra("User", user as Serializable)
        startActivity(intent)
    }
}


