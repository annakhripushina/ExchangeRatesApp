package com.example.exchangeratesapp.data.network

import com.example.exchangeratesapp.data.model.CurrencyListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/fetch-all")
    suspend fun getCurrencyList(
        @Query("api_key") apiKey: String,
        @Query("from") base: String?
    ): CurrencyListModel
}