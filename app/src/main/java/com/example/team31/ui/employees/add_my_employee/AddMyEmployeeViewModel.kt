package com.example.team31.ui.employees.add_my_employee

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.team31.Ansatt
import com.example.team31.Bruker
import com.google.firebase.database.FirebaseDatabase

class AddMyEmployeeViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    fun leggTilAnsatt(admin: Bruker, myEmployee: Ansatt) {
        val refAnsatt = FirebaseDatabase.getInstance().getReference("Ansatte").child(admin.id!!)



        val ansattId = refAnsatt.push().key
        myEmployee.ansattId = ansattId
        refAnsatt.child(ansattId!!).setValue(myEmployee).addOnCompleteListener {
            Log.i("Message:", "Ansatt registrert")
        }
    }
}