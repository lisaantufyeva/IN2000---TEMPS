package com.example.team31

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController


class AdminActivity : AppCompatActivity() {
    private lateinit var user: Bruker

    override fun onCreate(savedInstanceState: Bundle?) {
        user = intent.extras!!.get("User") as Bruker

        Log.i("userACT: ", user.toString())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_overview,
            R.id.navigation_employees,
            R.id.navigation_profile
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    fun getUserId(): String{
        return user.id!!
    }
    fun getUser(): Bruker{
        return user
    }
    fun updateUser(newUser:Bruker){
        user = newUser
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null){
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)
        }
    }
}