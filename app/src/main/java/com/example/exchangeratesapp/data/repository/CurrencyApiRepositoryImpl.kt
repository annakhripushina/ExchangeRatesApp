package com.example.exchangeratesapp.data.repository

import com.example.exchangeratesapp.data.model.CurrencyListModel
import com.example.exchangeratesapp.data.network.ApiService
import com.example.exchangeratesapp.domain.repository.CurrencyApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrencyApiRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    CurrencyApiRepository {

    override suspend fun getCurrencyList(code: String?): Flow<CurrencyListModel> =
        flow {
            emit(apiService.getCurrencyList("f1fa8641d4-10f79031ba-rdem5i", code))
        }.flowOn(Dispatchers.IO)

}