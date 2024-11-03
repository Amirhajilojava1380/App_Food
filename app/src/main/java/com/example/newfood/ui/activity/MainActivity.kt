package com.example.newfood.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.newfood.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private  lateinit var navController: NavController
    private  lateinit var appBarConfiguration: AppBarConfiguration
    private  lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.recipesFragment2,
                R.id.favoriteFragment2,
                R.id.foodJokeFragment2
            )
        )

        bottomNavigationView = findViewById(R.id.bottomnavigation)
        bottomNavigationView.setupWithNavController(navController)


    }




}