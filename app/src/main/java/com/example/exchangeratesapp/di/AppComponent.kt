package com.example.exchangeratesapp.di

import com.example.exchangeratesapp.di.module.DataModule
import com.example.exchangeratesapp.di.module.DomainModule
import com.example.exchangeratesapp.ui.popularRates.PopularRatesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(popularRatesFragment: PopularRatesFragment)
}