package com.example.team31.ui.employees.my_employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.Ansatt
import com.example.team31.databinding.EmployeeCardBinding


class MyEmployeeAdapter(private val fragment: Fragment, private val ansatte : MutableList<Ansatt> ):RecyclerView.Adapter<MyEmployeeAdapter.ViewHolder>() {


    class ViewHolder(view: EmployeeCardBinding) : RecyclerView.ViewHolder(view.root) {

        val name = view.tvName
        val email = view.tvEmail
        val rolle = view.tvRolle
        var editButton = view.editButton

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: EmployeeCardBinding =
            EmployeeCardBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val namePlaceholder = "Navn: "
        val emailPlaceholder="E-mail: "
        val rollePlaceholder = "Rolle: "


        val ansatt = ansatte[position]

            holder.name.text =namePlaceholder + ansatt.navn

            holder.email.text = emailPlaceholder + ansatt.email

            holder.rolle.text = rollePlaceholder +ansatt.rolle

        holder.editButton.setOnClickListener {
            edit(it, ansatt.ansattId!!)
        }




    }

    private fun edit(root: View, ansattId:String){
        val action = MyEmployeeFragmentDirections.actionNavigationEmployeesToEditEmployee(ansattId)

        Navigation.findNavController(root).navigate(action)
    }

    override fun getItemCount(): Int {
        return ansatte.size
    }

}


