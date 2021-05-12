package com.example.team31.ui.ansatt_interface.available_shifts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.AdminActivity
import com.example.team31.AnsattActivity
import com.example.team31.R
import com.example.team31.Varsel
import com.example.team31.ui.overview.week_overview.OverviewAdapter
import com.example.team31.ui.overview.week_overview.OverviewViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AvailableShiftsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AvailableShiftsListViewModel
    private lateinit var alerts: MutableList<Varsel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.available_shifts_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var ansattUser = (activity as AnsattActivity?)!!.getUser()
        val ansattId = (activity as AnsattActivity?)!!.getUserId()

        //val refK = FirebaseDatabase.getInstance().getReference("Ansatte").child(ansattUser.adminId!!)
        //println("Hentet UserID available shifts fragment:" + refK)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AvailableShiftsListViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerview)

/*
        alerts = mutableListOf<Varsel>()

        val alert1 = Varsel("12.mars", false)
        alerts.add(alert1)

        display(alerts)*/



        GlobalScope.launch(Dispatchers.IO){
            val recentAlerts = viewModel.getAlertList(ansattUser.adminId!!)

            withContext(Dispatchers.Main){
                alerts = recentAlerts
                Log.i("VarselListe:", alerts.toString())
                display(alerts)
            }
        }
    }

    private fun display(list: MutableList<Varsel>){
            recyclerView.also {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = AvailableShiftsAdapter(list, requireContext())
        }
    }

}