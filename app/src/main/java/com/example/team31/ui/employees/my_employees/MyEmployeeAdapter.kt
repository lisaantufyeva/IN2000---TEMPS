package com.example.team31.ui.employees.my_employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.Ansatt
import com.example.team31.databinding.EmployeeCardBinding

//displays each employee cards in recycleview
class MyEmployeeAdapter(private val fragment: Fragment, private val ansatte : MutableList<Ansatt> ):RecyclerView.Adapter<MyEmployeeAdapter.ViewHolder>() {


    class ViewHolder(view: EmployeeCardBinding) : RecyclerView.ViewHolder(view.root) {

        val name = view.tvName
        val email = view.tvEmail
        val rolle = view.tvRolle
        var editButton = view.editButton

    }
    //Inflates the cardview which is designed in the employee_card.xml

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: EmployeeCardBinding =
            EmployeeCardBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

 //binds each item in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //place holders
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

//fuction use to naviget to edit_Employee fragment
    private fun edit(root: View, ansattId:String){
        val action = MyEmployeeFragmentDirections.actionNavigationEmployeesToEditEmployee(ansattId)

        Navigation.findNavController(root).navigate(action)
    }
    //gets number of items on the list
    override fun getItemCount(): Int {
        return ansatte.size
    }

}


