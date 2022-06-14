package com.example.exchangeratesapp.di.module

import android.app.Application
import android.content.Context
import com.example.exchangeratesapp.data.network.ApiService
import com.example.exchangeratesapp.data.room.CurrencyDao
import com.example.exchangeratesapp.data.room.CurrencyDataBase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule(val application: Application) {
    companion object {
        var BASE_URL = "https://api.fastforex.io/"
    }

    @Singleton
    @Provides
    fun provideRetrofitInterface(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun getcurrencyDAO(currencyDataBase: CurrencyDataBase): CurrencyDao =
        currencyDataBase.currencyDao

    @Singleton
    @Provides
    fun getcurrencyDataBaseInstance(): CurrencyDataBase =
        CurrencyDataBase.getDatabase(provideAppContext())

    @Singleton
    @Provides
    fun provideAppContext(): Context =
        application.applicationContext
}