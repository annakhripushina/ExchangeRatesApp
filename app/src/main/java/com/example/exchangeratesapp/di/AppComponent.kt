package com.example.exchangeratesapp.di

import com.example.exchangeratesapp.di.module.DataModule
import com.example.exchangeratesapp.di.module.DomainModule
import com.example.exchangeratesapp.ui.favouriteCurrency.FavouriteCurrencyFragment
import com.example.exchangeratesapp.ui.popularCurrency.PopularCurrencyFragment
import com.example.exchangeratesapp.ui.sort.SortFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(popularCurrencyFragment: PopularCurrencyFragment)
    fun inject(sortFragment: SortFragment)
    fun inject(favouriteCurrencyFragment: FavouriteCurrencyFragment)
}