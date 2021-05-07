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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.team31.R
import com.example.team31.data.api.LocationForecastApi
import com.example.team31.databinding.DetailFragmentBinding
import com.example.team31.ui.overview.week_overview.OverviewViewModel
import com.example.team31.ui.overview.week_overview.OverviewViewModelFactory
import com.example.team31.ui.overview.week_overview.RefinedForecast

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var forecastObject: RefinedForecast


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        forecastObject = args.RefinedForecast
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //model = ViewModelProviders.of(this, factory).get(OverviewViewModel::class.java)

        val binding = DetailFragmentBinding.bind(view)
        binding.apply {
            date.text = forecastObject.time
            temp.text = forecastObject.temp
            precipitation.text = forecastObject.precipitation
            val currentImageId = context?.resources?.getIdentifier("@drawable/"+forecastObject.symbol, "drawable",
                context?.packageName)
            val currentDrawable = currentImageId?.let { context?.resources?.getDrawable(it) }
            imageView.setImageDrawable(currentDrawable)
        }

        /*
        model.getSelected()?.let { selectedItem ->
            with(binding) {
                date.text = selectedItem.time
                temp.text = selectedItem.temp
                precipitation.text = selectedItem.precipitation
            }
        }*/
        binding.sendMessage.setOnClickListener {
            sendMessage()
        }
    }
    private fun sendMessage(){
        Toast.makeText(context, "Send", Toast.LENGTH_SHORT).show()
    }

}

        /*
        //date
        val date: TextView = view.findViewById(R.id.date)
        date.text = forecastObject.time

        //temp
        val degrees: TextView = view.findViewById(R.id.temp)
        degrees.text = forecastObject.temp

        val precipitation: TextView = view.findViewById(R.id.precipitation)
        precipitation.text = forecastObject.precipitation

        //icon
        val image: ImageView = view.findViewById(R.id.imageView)
        val currentImageURI = "@drawable/"+forecastObject.symbol
        val currentImageId = context?.resources?.getIdentifier(currentImageURI, "drawable",
            context?.packageName
        )
        val currentDrawable = currentImageId?.let { context?.resources?.getDrawable(it) }
        image.setImageDrawable(currentDrawable)

        val button: Button = requireView().findViewById(R.id.sendMessage)
        button.setOnClickListener {
            sendMessage()
        }*/


/*
//model = ViewModelProviders.of(this, factory).get(OverviewViewModel::class.java)
        /*
        model.getSelected()?.let { selectedItem->
            with(binding){
                date.text = selectedItem.time
                temp.text = selectedItem.temp
                precipitation.text = selectedItem.precipitation
            }
        }
        binding.sendMessage.setOnClickListener {
            sendMessage()*/
        }

        /*
        val button: Button = requireView().findViewById(R.id.sendMessage)
        button.setOnClickListener {
 */