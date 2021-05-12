package com.example.team31.ui.ansatt_interface.available_shifts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.R
import com.example.team31.Varsel


class AvailableShiftsAdapter(private val shiftList: MutableList<Varsel>, val context: Context): RecyclerView.Adapter<AvailableShiftsAdapter.AvailableShiftAdapterHolder>() {

    class AvailableShiftAdapterHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.date)
        val button: Button = itemView.findViewById(R.id.accept_button)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableShiftAdapterHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.shift_card_layout, parent, false)

        return AvailableShiftAdapterHolder(view)
    }
    override fun onBindViewHolder(holder: AvailableShiftAdapterHolder, position: Int) {
        holder.date.text = shiftList[position].date
        holder.button.setOnClickListener {
            Toast.makeText(context, "Vakten er tatt", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = shiftList.size


}