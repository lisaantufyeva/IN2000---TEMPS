package com.example.team31.ui.overview.week_overview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

import androidx.core.view.isVisible

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.Bruker
import com.example.team31.R
import com.example.team31.Varsel


class OverviewAdapter(private val forecastList: List<RefinedForecast>, val context: Context, val user: Bruker,
                      private val availableAlerts:MutableList<Varsel>, private val acceptedAlerts:MutableList<Varsel>):
    RecyclerView.Adapter<OverviewAdapter.OverviewAdapterHolder>() {

    class OverviewAdapterHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.date)
        val temp: TextView = itemView.findViewById(R.id.temp)
        val icon: ImageView = itemView.findViewById(R.id.imageView)
        val staffButton: Button = itemView.findViewById(R.id.staff_button)
        val alertLable: Button = itemView.findViewById(R.id.alert_lable)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_cards_layout, parent, false)

        return OverviewAdapterHolder(view)
    }
    override fun onBindViewHolder(holder: OverviewAdapterHolder, position: Int) {
        val forecastObject = forecastList[position]
        holder.date.text = forecastList[position].time
        holder.temp.text = context.getString(R.string.degrees_symbol,forecastList[position].temp)

        val uri = "@drawable/" + forecastList[position].symbol
        println(forecastList[position].symbol)
        val iconId = context.resources.getIdentifier(uri, "drawable", context.packageName)
        val drawable = ContextCompat.getDrawable(context, iconId)
        holder.icon.setImageDrawable(drawable)

        if (checkCreatedAlerts(forecastList[position].time, availableAlerts) or checkAcceptedAlerts(forecastList[position].time, acceptedAlerts)){
            holder.alertLable.text = context.getString(R.string.varsel_sendt_mottatt,
                getcreatedTotal(forecastList[position].time, availableAlerts, acceptedAlerts).toString(),
                    getAcceptedShifts(forecastList[position].time, acceptedAlerts).toString())
            Log.i ("number of alert:", getNumberOfAlerts(forecastList[position].time, availableAlerts).toString() )
            Log.i ( "number of shifts: ", getAcceptedShifts(forecastList[position].time, acceptedAlerts).toString())
            holder.alertLable.isVisible = true
        } else {
            var extraStaff = 0
            if (checkLowStaffing(forecastList[position], user.triggerTemp, user.nedbor)){
                extraStaff = checkStaffingDemand(forecastList[position], user)

                holder.staffButton.isVisible = true
            }

            holder.staffButton.setOnClickListener {
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
