package com.example.team31.ui.employees.my_employees

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.team31.Ansatt
import com.example.team31.Bruker
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyEmployeesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "employees Fragment"
    }
    private var employees = mutableListOf<Ansatt>()

    val text: LiveData<String> = _text

    fun getUsers(admin: Bruker) {
        val refAnsatt = FirebaseDatabase.getInstance().getReference("Ansatte").child(admin.id!!)
        val ansatte = ArrayList<Ansatt>()

        // Henter brukere fra firebase
        val UserListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (i in dataSnapshot.children) {
                        val user = i.getValue(Ansatt::class.java)
                        ansatte.add(user!!)
                        Log.i("hei",ansatte.toString())
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("message", "loadPost:onCancelled", databaseError.toException())
            }
        }
        employees = ansatte
        refAnsatt.addValueEventListener(UserListener)
    }

    fun editAnsatt(adminId:String, ansattId:String, email: String, rolle:String, name:String){
        val ref = FirebaseDatabase.getInstance().getReference("Ansatte").child(adminId).child(ansattId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("email",email)
        hashMap.put("navn", name)
        hashMap.put("rolle", rolle)

        ref.updateChildren(hashMap)
    }
    fun leggTilAnsatt(admin: Bruker, myEmployee: Ansatt) {
        val refAnsatt = FirebaseDatabase.getInstance().getReference("Ansatte").child(admin.id!!)

        val ansattId = refAnsatt.push().key
        refAnsatt.child(ansattId!!).setValue(myEmployee).addOnCompleteListener {
            Log.i("Message:", "Ansatt registrert")
        }
    }


    fun getEmployees():MutableList<Ansatt>{
        return employees
    }
}