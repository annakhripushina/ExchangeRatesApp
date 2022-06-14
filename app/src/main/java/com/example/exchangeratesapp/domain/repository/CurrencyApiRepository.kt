package com.example.exchangeratesapp.domain.repository

import com.example.exchangeratesapp.data.model.CurrencyListModel
import kotlinx.coroutines.flow.Flow

interface CurrencyApiRepository {

    suspend fun getCurrencyList(code: String?): Flow<CurrencyListModel>

}