package com.example.team31.ui.overview.week_overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.AdminActivity
import com.example.team31.R
import com.example.team31.Bruker
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
    //private lateinit var user:Bruker




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }
    //("59.9", "10.75")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //user = (activity as AdminActivity?)!!.getUser()
        super.onViewCreated(view, savedInstanceState)
        val api = LocationForecastApi()
        val repository = ForecastRepository(api)
        factory = OverviewViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(OverviewViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerview)

        viewModel.getForecastList("59.9", "10.75")
        viewModel.forecastList.observe(viewLifecycleOwner, Observer { forecastList ->
            recyclerView.also {
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = OverviewAdapter(forecastList, requireContext())
            }
        })
    }
}
        /*

     fun displayWeatherList(root: View, list: List<RefinedForecast>){
        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = OverviewAdapter(list, root.context)

    }*/

         /*

    fun showDetail(weatherObject: RefinedForecast, root: View){
        val actionDetail = DetailFragmentDirections.actionDetail(weatherObject)
        Navigation.findNavController(root).navigate(actionDetail)
    }*/
/*
    val staffButton: Button = root.findViewById(R.id.staff_button)
    staffButton.setOnClickListener {
        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
    }*/



/*
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}*/


