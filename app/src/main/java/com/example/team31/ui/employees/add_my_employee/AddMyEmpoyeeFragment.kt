package com.example.team31.ui.employees.add_my_employee

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team31.*
import com.example.team31.databinding.AddmyemployeeFragmentBinding

import com.example.team31.databinding.DialogCustomListBinding


import com.example.team31.Ansatt

//Fragment to add an employee
class AddMyEmpoyeeFragment : Fragment() , View.OnClickListener{



    private lateinit var viewModel: AddMyEmployeeViewModel
    private lateinit var mBinding : AddmyemployeeFragmentBinding

    //global variabel for jobtitle
    private lateinit var mCustomListDialog: Dialog
    private lateinit var user: Bruker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View{
        mBinding = AddmyemployeeFragmentBinding.inflate(layoutInflater)
        mBinding.etRolle.setOnClickListener(this)
        mBinding.btnAddEmployee.setOnClickListener(this)

        viewModel = ViewModelProvider(this).get(AddMyEmployeeViewModel::class.java)

        return mBinding.root



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = (activity as AdminActivity?)!!.getUser()
    }









    private fun jobTiitleItemsListDialog(title: String, itemsList: List<String>, selection: String) {

        mCustomListDialog = Dialog(requireActivity())

        val binding: DialogCustomListBinding = DialogCustomListBinding.inflate(layoutInflater)


        mCustomListDialog.setContentView(binding.root)

        binding.tvTitle.text = title
      //seting up layout manager that recyclerview will use
        binding.rvList.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = JobTitleListItemAdapter(this, itemsList, selection)
    // adapter instance is set to the recyclerview
        binding.rvList.adapter = adapter
        //start dialog
        mCustomListDialog.show()
    }


//A function to set the selected item to the view.
    fun selectedListItem(item: String, selection: String) {

        when (selection) {

            JobTitleConstants.JOB_TITLE-> {
                //dialog window dismissed when a titile is chosen
                mCustomListDialog.dismiss()
                mBinding.etRolle.setText(item)
            }

        }    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {


                R.id.et_rolle -> {
                    jobTiitleItemsListDialog(
                            resources.getString(R.string.Rolle),
                            JobTitleConstants.rolleType(),
                            JobTitleConstants.JOB_TITLE
                    )
                    return


                }

                R.id.btn_add_employee ->{
                    val name = mBinding.etNavn.text.toString().trim {it <= ' '}
                    val email = mBinding.etEmail.text.toString().trim {it <= ' '}
                    val rolle = mBinding.etRolle.text.toString().trim {it <= ' '}


                    when{

                        TextUtils.isEmpty(name) -> {
                            Toast.makeText(
                                    activity,
                                    resources.getString(R.string.err_msg_select_name),
                                    Toast.LENGTH_SHORT
                            ).show()
                        }

                        TextUtils.isEmpty(email) || (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) -> {
                            Toast.makeText(
                                activity,
                                    resources.getString(R.string.err_msg_enter_email),
                                    Toast.LENGTH_SHORT
                            ).show()

                        }


                        TextUtils.isEmpty(rolle) -> {
                            Toast.makeText(
                                    activity,
                                    resources.getString(R.string.err_msg_select_rolle),
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                        else ->{

                            val ansatt = Ansatt(null,email, "temps31",name, rolle, user.id)
                            viewModel.leggTilAnsatt(user,ansatt)

                            findNavController().navigate(
                                    R.id.navigation_employees)
                            

                            Log.w("Insertion", "Success")
                        }
                    }

                }
            }
        }
    }
}

