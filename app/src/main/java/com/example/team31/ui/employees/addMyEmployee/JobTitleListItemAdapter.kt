package com.example.team31.ui.employees


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.team31.databinding.ItemCustomListLayoutBinding
import com.example.team31.ui.employees.addMyEmployee.AddMyEmpoyeeFragment


class JobTitleListItemAdapter(
private val activity:Fragment,
private val listItems: List<String>,
private val selection: String
) : RecyclerView.Adapter<JobTitleListItemAdapter.ViewHolder>() {

        //Inflates the item view for roles

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding: ItemCustomListLayoutBinding =
                    ItemCustomListLayoutBinding.inflate(LayoutInflater.from(activity.context), parent, false)
            return ViewHolder(binding)
        }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listItems[position]

        holder.tvText.text = item


        holder.itemView.setOnClickListener {

            if (activity is AddMyEmpoyeeFragment) {
                activity.selectedListItem(item, selection)
            }
        }

    }

    override fun getItemCount(): Int {
        return listItems.size
    }


            class ViewHolder(view: ItemCustomListLayoutBinding) : RecyclerView.ViewHolder(view.root) {
                // Holds the TextView that will add each item to
                val tvText = view.tvText
            }
        }





