package com.example.team31.ui.overview.week_overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team31.AdminActivity
import com.example.team31.data.api.LocationForecastApi
import com.example.team31.data.repositories.ForecastRepository
import com.example.team31.databinding.OverviewFragmentBinding
import android.os.Handler


class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var factory: OverviewViewModelFactory
    private lateinit var viewBinding: OverviewFragmentBinding


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
        viewModel.forecastList.observe(viewLifecycleOwner, Observer { forecastList ->
            viewBinding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
            Handler().postDelayed({
                viewBinding.recyclerview.setHasFixedSize(true)
                val overviewAdapter = OverviewAdapter(forecastList, requireContext(), user)
                viewBinding.recyclerview.adapter = overviewAdapter
                overviewAdapter.notifyDataSetChanged()
            }, 500)
        })
    }
}


