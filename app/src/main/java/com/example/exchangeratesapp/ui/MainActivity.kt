package com.example.exchangeratesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.ui.favouriteRates.FavouriteRatesFragment
import com.example.exchangeratesapp.ui.popularRates.PopularRatesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, PopularRatesFragment())
                .commit()
        }

        val navigate: BottomNavigationView = findViewById(R.id.bottomNavigate)

        navigate.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_popular -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, PopularRatesFragment(), "PopularRatesFragment")
                        .commit()
                }
                R.id.nav_favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, FavouriteRatesFragment(), "FavouriteRatesFragment")
                        .commit()
                }
            }
            true
        }

    }
}