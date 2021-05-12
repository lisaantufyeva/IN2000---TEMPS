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
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.AdminActivity
import com.example.team31.R
import com.example.team31.data.api.LocationForecastApi
import com.example.team31.data.repositories.ForecastRepository
import java.nio.channels.Selector

//oppdatert Fragment

class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var itemSelector: Selector

    private lateinit var factory: OverviewViewModelFactory
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //heiprivate var userId:String = ""


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {
        //var user = (activity as AdminActivity?)!!.getUser() //Henrik
        return inflater.inflate(R.layout.overview_fragment, container,false)
    }
    //("59.9", "10.75")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var user = (activity as AdminActivity?)!!.getUser()
        Log.i("id:", user.toString())
        super.onViewCreated(view, savedInstanceState)
        val api = LocationForecastApi()
        val repository = ForecastRepository(api)

        factory = OverviewViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(OverviewViewModel::class.java)
        //val user = viewModel.getUser(userId)
        recyclerView = view.findViewById(R.id.recyclerview)
        //val user = viewModel.getMainUser()
        viewModel.getForecastList(user.latitude!!, user.longitude!!)
        viewModel.forecastList.observe(viewLifecycleOwner, Observer { forecastList ->
            recyclerView.also {
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = OverviewAdapter(forecastList, requireContext(),user)
            }
        })
    }
}


