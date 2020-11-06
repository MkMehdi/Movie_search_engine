package com.sample.moviesearchengine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import com.sample.moviesearchengine.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.navHost))
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHost)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}