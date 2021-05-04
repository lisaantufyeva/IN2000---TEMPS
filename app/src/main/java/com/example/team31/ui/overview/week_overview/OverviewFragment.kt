package com.example.team31.ui.overview.week_overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.AdminActivity
import com.example.team31.Bruker

import com.example.team31.R
import com.example.team31.ui.overview.detail.DetailFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//oppdatert Fragment

class OverviewFragment : Fragment(), OverviewContract.View {


    private lateinit var presenter: OverviewPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var user: Bruker



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {



        val root: View = inflater.inflate(R.layout.overview_fragment, container, false)
        presenter = OverviewPresenter(root.context, OverviewModel())


        CoroutineScope(Dispatchers.Main).launch {
            val response = presenter.getForecastList()
            user = (activity as AdminActivity?)!!.getUser()
            displayWeatherList(root, response, user)

        }
        return root
    }

     fun displayWeatherList(root: View, list: List<RefinedForecast>, user:Bruker){
        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = OverviewAdapter(list, root.context, user)

    }

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




    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}


