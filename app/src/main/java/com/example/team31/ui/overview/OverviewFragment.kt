package com.example.team31.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.team31.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class OverviewFragment : Fragment(), OverviewContract.View {


    private lateinit var presenter: OverviewPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {

        val root: View = inflater.inflate(R.layout.overview_fragment, container, false)
        presenter = OverviewPresenter(root.context, OverviewModel())

        CoroutineScope(Dispatchers.Main).launch {
            val response = presenter.getForecastList()
            display(root, response)
        }

        return root
    }

     fun display(root: View, list: MutableList<Forecast>){
        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = OverviewAdapter(list)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}


