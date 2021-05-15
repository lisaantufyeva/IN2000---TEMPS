package com.example.team31.ui.ansatt_interface.my_shifts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.R
import com.example.team31.Varsel


class MyShiftsAdapter(private val shiftList: List<Varsel>, val context: Context): RecyclerView.Adapter<MyShiftsAdapter.MyShiftsAdapterHolder>() {

    class MyShiftsAdapterHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.date)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyShiftsAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_shift_card, parent, false)
        return MyShiftsAdapterHolder(view)
    }
    override fun onBindViewHolder(holder: MyShiftsAdapterHolder, position: Int) {
        holder.date.text = shiftList[position].date
    }
    override fun getItemCount() = shiftList.size
}