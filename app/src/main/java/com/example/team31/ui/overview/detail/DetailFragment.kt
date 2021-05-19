package com.example.team31.ui.overview.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.team31.AdminActivity
import com.example.team31.R
import com.example.team31.Varsel
import com.example.team31.databinding.DetailFragmentBinding
import com.example.team31.ui.overview.week_overview.RefinedForecast
import com.example.team31.ui.overview.week_overview.getAcceptedShifts
import com.example.team31.ui.overview.week_overview.getNumberOfAlerts


class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var forecastObject: RefinedForecast
    private lateinit var detailViewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel =
                ViewModelProvider(this).get(DetailViewModel::class.java)
        forecastObject = args.RefinedForecast
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val admin = (activity as AdminActivity?)!!.getUser()
        val userId = (activity as AdminActivity?)!!.getUserId()

        super.onViewCreated(view, savedInstanceState)

        val binding = DetailFragmentBinding.bind(view)
        binding.apply {
            date.text = forecastObject.time
            temp.text = forecastObject.temp
            precipitation.text = this@DetailFragment.getString(R.string.detail_precipitation,
                    forecastObject.precipitation)
            extraStaff.text = this@DetailFragment.getString(R.string.detail_extra_staffing,
                    args.extraStaff.toString())
            currentStaff.text = this@DetailFragment.getString(R.string.detail_current_staffing,
                    admin.normalBemanning)
            val currentImageId = context?.resources?.getIdentifier("@drawable/"+forecastObject.symbol, "drawable",
                context?.packageName)
            val currentDrawable = currentImageId?.let { context?.let { it1 -> ContextCompat.getDrawable(it1,it) } }
            imageView.setImageDrawable(currentDrawable)
        }

        binding.sendMessage.setOnClickListener {
            val alertList = createAlertList(forecastObject.time, args.extraStaff, userId)
            sendMessage(alertList)
            binding.sendMessage.isInvisible = true

        }
    }
    private fun sendMessage(list: MutableList<Varsel>){
        Toast.makeText(context, "Send", Toast.LENGTH_SHORT).show()
        for ( i in list){
            detailViewModel.addAlert(i)
        }
    }

    private fun createAlertList(date: String, extraStaff: Int, userId: String): MutableList<Varsel>{
        return MutableList(extraStaff){Varsel(null,date, false,null,userId)}
    }

}

