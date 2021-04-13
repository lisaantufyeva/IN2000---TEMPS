package com.example.team31.ui.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.R
import com.example.team31.data.api.ForecastDto

class OverviewAdapter(val forecastList: MutableList<Forecast>):
    RecyclerView.Adapter<OverviewAdapter.OverviewAdapterHolder>() {

    class OverviewAdapterHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.date)
        val temp: TextView = itemView.findViewById(R.id.temp)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cards_layout, parent, false)

        return OverviewAdapterHolder(view)
    }
    override fun onBindViewHolder(holder: OverviewAdapterHolder, position: Int) {
        holder.date.text = forecastList[position].time
        holder.temp.text = forecastList[position].temp

    }

    override fun getItemCount() = forecastList.size

}