package com.example.exchangeratesapp.domain.repository

import com.example.exchangeratesapp.data.model.LatestModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Header

interface RatesApiRepository {

    suspend fun getLatest(code: String?): Flow<LatestModel>
}