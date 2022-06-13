package com.example.exchangeratesapp.data.network

import com.example.exchangeratesapp.data.model.LatestModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface ApiService {
    @GET("/fetch-all")
    suspend fun getLatest(
        @Query("api_key") apiKey: String,
        @Query("from") base: String?
    ): LatestModel
}