package com.example.team31

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import java.io.Serializable

class Authentication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }

    fun startActivity2(i:Bruker){
        val intent = Intent(applicationContext, AdminActivity::class.java)
        intent.putExtra("User", i as Serializable)
        startActivity(intent)
    }

    fun startAnsattActivity(i:Ansatt){
        val intent = Intent(applicationContext, AnsattActivity::class.java)
        intent.putExtra("Ansatt", i as Serializable)
        startActivity(intent)
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null){
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)
        }
    }
}