package com.example.team31

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class AnsattActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ansatt)

        val navViewAnsatt: BottomNavigationView = findViewById(R.id.nav_view_ansatt)

        val navController = findNavController(R.id.nav_host_fragment_ansatt)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_available_shifts,
            R.id.navigation_profile_ansatt,
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navViewAnsatt.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_ansatt)
        return navController.navigateUp()
    }

}