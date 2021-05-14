package com.example.team31.ui.ansatt_interface.my_shifts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.AnsattActivity
import com.example.team31.R
import com.example.team31.Varsel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyShiftsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MyShiftsViewModel
    private lateinit var myshifts: MutableList<Varsel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_shifts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var ansattUser = (activity as AnsattActivity?)!!.getUser()

        //val refK = FirebaseDatabase.getInstance().getReference("Ansatte").child(ansattUser.adminId!!)
        //println("Hentet UserID available shifts fragment:" + refK)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MyShiftsViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerview)
        GlobalScope.launch(Dispatchers.IO){
            val recentAlerts = viewModel.getMyShiftList(ansattUser.adminId!!, ansattUser.ansattId!!)
            withContext(Dispatchers.Main){
                myshifts = recentAlerts
                Log.i("VarselListe:", myshifts.toString())
                display(myshifts, ansattUser)
            }
        }
        /*
    GlobalScope.launch(Dispatchers.IO){
        viewModel.getAlertList(ansattUser.adminId!!)
        withContext(Dispatchers.Main){
            viewModel.alertList.observe(viewLifecycleOwner, Observer{ alerts ->
                recyclerView.also {
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = AvailableShiftsAdapter(alerts, requireContext())
                }
            })
        }
    }*/
        /*
            withContext(Dispatchers.Main){
                alerts = recentAlerts
                Log.i("VarselListe:", alerts.toString())
                display(alerts)
            }
        }
        viewModel.getAlertList(ansattUser.adminId!!)
        viewModel.alertList.observe(viewLifecycleOwner, Observer { alertList ->
            recyclerView.also {
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = AvailableShiftsAdapter(alertList, requireContext())
            }
        })*/

    }

    private fun display(list: MutableList<Varsel>, ansattUser: com.example.team31.Ansatt){
        recyclerView.also {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = MyShiftsAdapter(list, requireContext(), ansattUser)
        }
    }

}