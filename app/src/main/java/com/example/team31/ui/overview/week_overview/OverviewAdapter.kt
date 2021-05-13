package com.example.team31.ui.overview.week_overview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.Bruker
import com.example.team31.R



class OverviewAdapter(private val forecastList: List<RefinedForecast>, val context: Context, val user: Bruker):
    RecyclerView.Adapter<OverviewAdapter.OverviewAdapterHolder>() {

    class OverviewAdapterHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.date)
        val temp: TextView = itemView.findViewById(R.id.temp)
        val icon: ImageView = itemView.findViewById(R.id.imageView)
        val staffButton: Button = itemView.findViewById(R.id.staff_button)
        val alertButton: Button = itemView.findViewById(R.id.alert_lable)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_cards_layout, parent, false)

        return OverviewAdapterHolder(view)
    }
    override fun onBindViewHolder(holder: OverviewAdapterHolder, position: Int) {
        val forecastObject = forecastList[position]
        holder.date.text = forecastList[position].time
        holder.temp.text = forecastList[position].temp + "°"

        val uri = "@drawable/" + forecastList[position].symbol
        val iconId = context.resources.getIdentifier(uri, "drawable", context.packageName)
        val drawable = context.resources.getDrawable(iconId)
        holder.icon.setImageDrawable(drawable)

        println( "VarselListe Adapter:" + user.varselListe)
        if (checkCreatedAlerts(forecastList[position].time, user.varselListe)){
            holder.alertButton.text = "Sendt: "+ getNumberOfAlerts(forecastList[position].time, user.varselListe) + "/ Tatt: "+ getAcceptedShifts(forecastList[position].time, user.varselListe).toString()
            holder.alertButton.isVisible = true
        } else {
            var extraStaff = 0
            if (checkLowStaffing(forecastList[position], user.triggerTemp)){
                extraStaff = checkStaffingDemand(forecastList[position], user)
                println("Dato"+forecastList[position].time)
                Log.d("Testing staffingDemang", checkStaffingDemand(forecastList[position], user).toString())

                //varselgenerator

                holder.staffButton.isVisible = true
            }

            holder.staffButton.setOnClickListener {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                showDetail(forecastObject,extraStaff, it)
            }
        }
    }
    override fun getItemCount() = forecastList.size

}

fun showDetail(weatherObject: RefinedForecast, extraStaff: Int,  root: View){
    val actionDetail = OverviewFragmentDirections.actionDetail(weatherObject, extraStaff)
    Navigation.findNavController(root).navigate(actionDetail)
}
