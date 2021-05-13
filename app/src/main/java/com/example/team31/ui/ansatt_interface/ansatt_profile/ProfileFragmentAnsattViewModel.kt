package com.example.team31.ui.ansatt_interface.ansatt_profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase

class ProfileFragmentAnsattViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _text = MutableLiveData<String>().apply {
        value = "Profile fragment ansatt"
    }
    fun update_password(password:String, userId: String, ansattId:String){
        val ref = FirebaseDatabase.getInstance().getReference("Ansatte").child(userId).child(ansattId)
        val hashMap =  hashMapOf<String, Any?>()
        hashMap.put("passord",password)

        ref.updateChildren(hashMap)
    }
}