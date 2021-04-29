package com.example.team31.ui.overview.detail

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.team31.R
import com.example.team31.ui.overview.week_overview.RefinedForecast

class DetailFragment : Fragment() {


    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    val args:DetailFragmentArgs by navArgs()
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //date
        val date: TextView = view.findViewById(R.id.date)
        val currentDate = args.RefinedForecast.time
        date.text = currentDate

        //temp
        val degrees: TextView = view.findViewById(R.id.temp)
        val currentDegrees = args.RefinedForecast.temp
        degrees.text = currentDegrees

        val precipitation: TextView = view.findViewById(R.id.precipitation)
        val currentP = args.RefinedForecast.precipitation
        precipitation.text = currentP

        //icon

        val image: ImageView = view.findViewById(R.id.imageView)
        val currentImageURI = "@drawable/"+args.RefinedForecast.symbol
        val currentImageId = context?.resources?.getIdentifier(currentImageURI, "drawable",
            context?.packageName
        )
        val currentDrawable = currentImageId?.let { context?.resources?.getDrawable(it) }
        image.setImageDrawable(currentDrawable)

    }
    fun onButtonClicked(){
        Toast.makeText(context, "Send", Toast.LENGTH_SHORT).show()
    }
/*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let{DetailFragmentArgs.fromBundle(it)}
        bindUI()
        //viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        // TODO: Use the ViewModel
    }*/


}