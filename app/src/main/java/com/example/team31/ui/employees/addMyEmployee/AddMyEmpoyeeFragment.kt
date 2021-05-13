package com.example.team31.ui.employees.addMyEmployee

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team31.*
import com.example.team31.databinding.AddmyemployeeFragmentBinding

import com.example.team31.databinding.DialogCustomListBinding
import com.example.team31.ui.employees.JobTitleListItemAdapter

import com.example.team31.Ansatt
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern.compile

class AddMyEmpoyeeFragment : Fragment() , View.OnClickListener{



    private lateinit var viewModel: AddMyEmployeeViewModel
    private lateinit var mBinding : AddmyemployeeFragmentBinding
    private lateinit var mCustomListDialog: Dialog
    private lateinit var user: Bruker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = AddmyemployeeFragmentBinding.inflate(layoutInflater)
        mBinding.etRolle.setOnClickListener(this)
        mBinding.btnAddEmployee.setOnClickListener(this)

        return mBinding.root



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = (activity as AdminActivity?)!!.getUser()
    }




    fun leggTilAnsatt(admin: Bruker, myEmployee: Ansatt) {
        val refAnsatt = FirebaseDatabase.getInstance().getReference("Ansatte").child(admin.id!!)

        val ansattId = refAnsatt.push().key
        refAnsatt.child(ansattId!!).setValue(myEmployee).addOnCompleteListener {
            Log.i("Message:", "Ansatt registrert")
        }
    }




    private fun customItemsListDialog(title: String, itemsList: List<String>, selection: String) {

        mCustomListDialog = Dialog(requireActivity())

        val binding: DialogCustomListBinding = DialogCustomListBinding.inflate(layoutInflater)


        mCustomListDialog.setContentView(binding.root)

        binding.tvTitle.text = title

        binding.rvList.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = JobTitleListItemAdapter(this, itemsList, selection)

        binding.rvList.adapter = adapter
        //start dialog
        mCustomListDialog.show()
    }



    fun selectedListItem(item: String, selection: String) {

        when (selection) {

            JobTitleConstants.JOB_TITLE-> {
                mCustomListDialog.dismiss()
                mBinding.etRolle.setText(item)
            }

        }    }

//email validation
    private val emailRegex = compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {


                R.id.et_rolle -> {
                    customItemsListDialog(
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

                        TextUtils.isEmpty(email)-> {
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
                            val ansatt = Ansatt(null,name, email,"temps31", rolle, user.id)
                            leggTilAnsatt(user,ansatt)

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

