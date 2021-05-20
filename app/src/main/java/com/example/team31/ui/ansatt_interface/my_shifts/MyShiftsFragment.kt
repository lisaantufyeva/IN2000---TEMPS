package com.example.team31.ui.ansatt_interface.my_shifts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team31.AnsattActivity
import com.example.team31.databinding.MyShiftsFragmentBinding

class MyShiftsFragment : Fragment() {

    private lateinit var viewModel: MyShiftsViewModel
    private lateinit var viewBinding: MyShiftsFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewBinding = MyShiftsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ansattUser = (activity as AnsattActivity?)!!.getUser()

        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MyShiftsViewModel::class.java)
        viewModel.getMyAcceptedShifts(ansattUser.adminId!!, ansattUser.ansattId!!)
        viewModel.myShifts.observe(viewLifecycleOwner, { myShifts ->
            viewBinding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
            Handler(Looper.getMainLooper()).postDelayed({
                viewBinding.recyclerview.setHasFixedSize(true)
                val myShiftsAdapter = MyShiftsAdapter(myShifts, requireContext())
                viewBinding.recyclerview.adapter = myShiftsAdapter
                myShiftsAdapter.notifyDataSetChanged()
            }, 500)
        })
    }
}
