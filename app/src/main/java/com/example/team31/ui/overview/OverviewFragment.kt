package com.example.team31.ui.overview

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.team31.R

class OverviewFragment : Fragment() {
/*
    companion object {
        fun newInstance() = OverviewFragment()
    }*/

    private lateinit var overviewViewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        overviewViewModel =
            ViewModelProvider(this).get(OverviewViewModel::class.java)
        val root = inflater.inflate(R.layout.overview_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_overview)
        val dateText: TextView = root.findViewById(R.id.date)
        val degreesText: TextView = root.findViewById(R.id.temp)
        val icon: ImageView = root.findViewById(R.id.imageView)

        overviewViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        overviewViewModel.date.observe(viewLifecycleOwner, Observer {
            dateText.text = it
        })
        overviewViewModel.degrees.observe(viewLifecycleOwner, Observer {
            degreesText.text = it
        })
        return root
    }
    /*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

    }*/
}