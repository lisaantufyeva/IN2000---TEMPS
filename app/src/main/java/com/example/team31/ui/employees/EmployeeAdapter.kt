package com.example.team31.ui.employees

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.team31.databinding.ActivityLeggTilRedigerAnsattBinding
import com.example.team31.databinding.EmployeeCardBinding

class EmployeeAdapter(private val fragment: Fragment,private val ansatte : MutableList<Ansatt> ):RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {


    class ViewHolder(view: EmployeeCardBinding) : RecyclerView.ViewHolder(view.root) {

        val name = view.tvName
        val email = view.tvEmail
        val rolle = view.tvRolle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: EmployeeCardBinding =
            EmployeeCardBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ansatt = ansatte.get(position)

            holder.name.text = "Navn: ${ansatt.navn}"

            holder.email.text = "E-mail: ${ansatt.email}"

            holder.rolle.text = "Rolle: ${ansatt.rolle}"



    }

    override fun getItemCount(): Int {
        return ansatte!!.size
    }

//    fun add(ansatt: Ansatt) {
//        ansatte.add(ansatt)
//       notifyDataSetChanged()
//    }

}


