package com.example.team31.ui.overview.week_overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team31.AdminActivity
import com.example.team31.data.api.LocationForecastApi
import com.example.team31.data.repositories.ForecastRepository
import com.example.team31.databinding.OverviewFragmentBinding
import android.os.Handler
import android.os.Looper
import com.example.team31.Varsel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var factory: OverviewViewModelFactory
    private lateinit var viewBinding: OverviewFragmentBinding
    private var availableAlerts = mutableListOf<Varsel>()
    private var acceptedAlerts = mutableListOf<Varsel>()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {
        viewBinding= OverviewFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = (activity as AdminActivity?)!!.getUser()
        Log.i("id:", user.toString())
        super.onViewCreated(view, savedInstanceState)

        val api = LocationForecastApi()
        val repository = ForecastRepository(api)

        factory = OverviewViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(OverviewViewModel::class.java)
        viewModel.getForecastList(user.latitude!!, user.longitude!!)

        GlobalScope.launch(Dispatchers.IO){
            val availableShifts = viewModel.getAvailableShiftsList(user.id!!)
            val acceptedShifts = viewModel.getAcceptedShiftsList(user.id!!)

            withContext(Dispatchers.Main){
                availableAlerts = availableShifts
                Log.i ("available: ", availableAlerts.toString())
                acceptedAlerts = acceptedShifts
                Log.i("accepted:", acceptedAlerts.toString())

                viewModel.forecastList.observe(viewLifecycleOwner,  { forecastList ->
                    viewBinding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
                    Handler(Looper.getMainLooper()).postDelayed({
                        viewBinding.recyclerview.setHasFixedSize(true)
                        val overviewAdapter = OverviewAdapter(forecastList, requireContext(), user, availableAlerts, acceptedAlerts)
                        viewBinding.recyclerview.adapter = overviewAdapter
                        overviewAdapter.notifyDataSetChanged()
                    }, 500)
                })

            }
        }

    }

}


