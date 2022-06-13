package com.example.exchangeratesapp.domain.usecase

import com.example.exchangeratesapp.data.model.LatestModel
import kotlinx.coroutines.flow.Flow

interface RatesApiUseCase {
    suspend fun getLatest(code: String?): Flow<LatestModel>
}