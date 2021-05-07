package com.example.team31

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.team31.ui.employees.EmployeeAdapter

//import android.support.v4.app.FragmentActivity


class AdminActivity : AppCompatActivity() {
    private lateinit var user:Bruker

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
            R.id.navigation_overview, R.id.navigation_employees, R.id.navigation_settings, R.id.navigation_profile))
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
}