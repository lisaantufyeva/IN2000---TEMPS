//package com.example.team31.ui.employees
//
//import android.app.Dialog
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.text.TextUtils
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.team31.Bruker
//import com.example.team31.ui.employees.addMyEmployee.JobTitleConstants
//import com.example.team31.R
//import com.example.team31.databinding.ActivityLeggTilRedigerAnsattBinding
//import com.example.team31.databinding.DialogCustomListBinding
//import com.example.team31.ui.employees.myEmployees.MyEmployee
//import com.example.team31.ui.employees.myEmployees.MyEmployeeAdapter
//import com.google.firebase.database.FirebaseDatabase
//
//class LeggTilRedigerAnsatt : AppCompatActivity(), View.OnClickListener {
//
//    //private val model: EmployeesViewModel by activityViewModels()
//
//    private lateinit var mBinding: ActivityLeggTilRedigerAnsattBinding
//    private lateinit var mCustomListDialog: Dialog
//    //private lateinit var Employ : EmployeesFragment
//    private lateinit var adapterMy: MyEmployeeAdapter
//    private lateinit var user:Bruker
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mBinding = ActivityLeggTilRedigerAnsattBinding.inflate(layoutInflater)
//        setContentView(mBinding.root)
//        user = intent.extras!!.get("User") as Bruker
//
//        setUpActionBar()
//        mBinding.etRolle.setOnClickListener(this@LeggTilRedigerAnsatt)
//        mBinding.btnAddEmployee.setOnClickListener(this@LeggTilRedigerAnsatt)
//        //Employ.add(drganaa)
//
//    }
//
//    fun leggTilAnsatt(admin: Bruker, myEmployee: MyEmployee) {
//        val refAnsatt = FirebaseDatabase.getInstance().getReference("Ansatte").child(admin.id!!)
//
//        val ansattId = refAnsatt.push().key
//        refAnsatt.child(ansattId!!).setValue(myEmployee).addOnCompleteListener {
//            Log.i("Message:", "Ansatt registrert")
//        }
//    }
//
//    private fun setUpActionBar() {
//        setSupportActionBar(mBinding.toolbarLeggTilAnsattActivity)
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        //back button
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
//
//        mBinding.toolbarLeggTilAnsattActivity.setNavigationOnClickListener { onBackPressed() }
//    }
//
//
//
//    private fun customItemsListDialog(title: String, itemsList: List<String>, selection: String) {
//
//        mCustomListDialog = Dialog(this@LeggTilRedigerAnsatt)
//
//        val binding: DialogCustomListBinding = DialogCustomListBinding.inflate(layoutInflater)
//
//
//        mCustomListDialog.setContentView(binding.root)
//
//        binding.tvTitle.text = title
//
//        binding.rvList.layoutManager = LinearLayoutManager(this@LeggTilRedigerAnsatt)
//
//        val adapter = JobTitleListItemAdapter(this@LeggTilRedigerAnsatt, itemsList, selection)
//
//        binding.rvList.adapter = adapter
//        //start dialog
//        mCustomListDialog.show()
//    }
//
//
//
//    fun selectedListItem(item: String, selection: String) {
//
//        when (selection) {
//
//            JobTitleConstants.ROLLE_TYPE-> {
//                mCustomListDialog.dismiss()
//                mBinding.etRolle.setText(item)
//            }
//
//        }    }
//
//
//
//
//    override fun onClick(v: View?) {
//        if (v != null) {
//            when (v.id) {
//
//
//                R.id.et_rolle -> {
//                    customItemsListDialog(
//                        resources.getString(R.string.Rolle),
//                        JobTitleConstants.rolleType(),
//                        JobTitleConstants.ROLLE_TYPE
//                    )
//                    return
//
//
//                }
//
//               R.id.btn_add_employee ->{
//                   val name = mBinding.etNavn.text.toString().trim {it <= ' '}
//                   val email = mBinding.etEmail.text.toString().trim {it <= ' '}
//                   val rolle = mBinding.etRolle.text.toString().trim {it <= ' '}
//
//
//                   when{
//
//                       TextUtils.isEmpty(name) -> {
//                           Toast.makeText(
//                               this@LeggTilRedigerAnsatt,
//                               resources.getString(R.string.err_msg_select_name),
//                               Toast.LENGTH_SHORT
//                           ).show()
//                       }
//
//                       TextUtils.isEmpty(email) -> {
//                           Toast.makeText(
//                               this@LeggTilRedigerAnsatt,
//                               resources.getString(R.string.err_msg_enter_email),
//                               Toast.LENGTH_SHORT
//                           ).show()
//                       }
//
//                       TextUtils.isEmpty(rolle) -> {
//                           Toast.makeText(
//                               this@LeggTilRedigerAnsatt,
//                               resources.getString(R.string.err_msg_select_rolle),
//                               Toast.LENGTH_SHORT
//                           ).show()
//                       }
//                       else ->{
//                           val ansatt = MyEmployee(name, email,rolle)
//                           leggTilAnsatt(user,ansatt)
//
//
//                           Toast.makeText(
//                               this@LeggTilRedigerAnsatt,
//                               "Alle verdig er gyldige",
//                               Toast.LENGTH_SHORT
//                           ).show()
//
//                           Log.w("Insertion", "Success")
//                           finish()
//                       }
//                   }
//
//               }
//            }
//        }
//    }
//}
//
//
