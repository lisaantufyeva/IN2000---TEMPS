package com.example.team31.ui.ansatt_interface.available_shifts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.team31.R

class AvailableShiftsListFragment : Fragment() {

    companion object {
        fun newInstance() = AvailableShiftsListFragment()
    }

    private lateinit var viewModel: AvailableShiftsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.available_shifts_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AvailableShiftsListViewModel::class.java)
        // TODO: Use the ViewModell@
    }

}