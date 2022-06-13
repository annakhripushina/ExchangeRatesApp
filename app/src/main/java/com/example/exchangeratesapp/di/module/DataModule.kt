package com.example.exchangeratesapp.di.module

import android.app.Application
import android.content.Context
import com.example.exchangeratesapp.BuildConfig
import com.example.exchangeratesapp.data.network.ApiService
import com.example.exchangeratesapp.data.room.RatesDao
import com.example.exchangeratesapp.data.room.RatesDataBase
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class DataModule(val application: Application) {
    companion object {
        var BASE_URL = "https://api.fastforex.io/"
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                return@addInterceptor chain.proceed(
//                    chain
//                        .request()
//                        .newBuilder()
//                        .addHeader(
//                            "access_key",
//                            "Y04mJuIDKth1OUteK4oMo7NhmKHSbkF6"
//                        )
//                        .build()
//                )
//            }
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    })
            .build()
        return okHttpClient
    }

    @Singleton
    @Provides
    fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        //.addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun getRatesDAO(ratesDataBase: RatesDataBase): RatesDao =
        ratesDataBase.ratesDao

    @Singleton
    @Provides
    fun getRatesDataBaseInstance(): RatesDataBase =
        RatesDataBase.getDatabase(provideAppContext())

    @Singleton
    @Provides
    fun provideAppContext(): Context =
        application.applicationContext
}