package com.example.exchangeratesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.ui.favouriteCurrency.FavouriteCurrencyFragment
import com.example.exchangeratesapp.ui.popularCurrency.PopularCurrencyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, PopularCurrencyFragment())
                .commit()
        }

        val navigate: BottomNavigationView = findViewById(R.id.bottomNavigate)

        navigate.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_popular -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainerView,
                            PopularCurrencyFragment(),
                            "PopularCurrencyFragment"
                        )
                        .commit()
                }
                R.id.nav_favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainerView,
                            FavouriteCurrencyFragment(),
                            "PopularCurrencyFragment"
                        )
                        .commit()
                }
            }
            true
        }

    }
}