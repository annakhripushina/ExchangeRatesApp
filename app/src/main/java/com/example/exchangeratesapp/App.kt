package com.example.exchangeratesapp

import android.app.Application
import com.example.exchangeratesapp.di.AppComponent
import com.example.exchangeratesapp.di.DaggerAppComponent
import com.example.exchangeratesapp.di.module.DataModule

class App : Application() {
    val appComponent: AppComponent =
        DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .build()
}