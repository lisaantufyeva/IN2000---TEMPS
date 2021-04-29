package com.example.team31.ui.overview.week_overview

import android.content.Context
import android.content.Intent
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

import com.example.team31.R
import com.example.team31.ui.overview.detail.DetailFragment


class OverviewAdapter(val forecastList: List<RefinedForecast>, val context: Context):
    RecyclerView.Adapter<OverviewAdapter.OverviewAdapterHolder>() {

    class OverviewAdapterHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.date)
        val temp: TextView = itemView.findViewById(R.id.temp)
        val icon: ImageView = itemView.findViewById(R.id.imageView)
        val staffButton: Button = itemView.findViewById(R.id.staff_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cards_layout, parent, false)


        return OverviewAdapterHolder(view)
    }
    override fun onBindViewHolder(holder: OverviewAdapterHolder, position: Int) {

        holder.date.text = forecastList[position].time
        holder.temp.text = forecastList[position].temp + "°"

        val uri = "@drawable/" + forecastList[position].symbol
        val iconId = context.resources.getIdentifier(uri, "drawable", context.packageName)
        val drawable = context.resources.getDrawable(iconId)
        holder.icon.setImageDrawable(drawable)

        if (checkLowStaffing(forecastList[position], 10.0)){
            holder.staffButton.isVisible = true
        }


        holder.staffButton.setOnClickListener {
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            showDetail(forecastList[position], it)

        }
    }
    override fun getItemCount() = forecastList.size

}

fun showDetail(weatherObject: RefinedForecast, root: View){
    val actionDetail = OverviewFragmentDirections.actionDetail(weatherObject)
    Navigation.findNavController(root).navigate(actionDetail)
}
