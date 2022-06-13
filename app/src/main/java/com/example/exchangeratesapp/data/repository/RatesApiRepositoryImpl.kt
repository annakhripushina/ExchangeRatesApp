package com.example.exchangeratesapp.data.repository

import com.example.exchangeratesapp.data.model.LatestModel
import com.example.exchangeratesapp.data.network.ApiService
import com.example.exchangeratesapp.domain.repository.RatesApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RatesApiRepositoryImpl @Inject constructor(private val apiService: ApiService)  : RatesApiRepository {
    override suspend fun getLatest(code: String?): Flow<LatestModel> =
        flow{
            emit(apiService.getLatest("f1fa8641d4-10f79031ba-rdem5i", code))
        }.flowOn(Dispatchers.IO)

}