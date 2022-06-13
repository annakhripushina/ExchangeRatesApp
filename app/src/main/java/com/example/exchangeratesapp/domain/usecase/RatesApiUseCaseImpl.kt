package com.example.exchangeratesapp.domain.usecase

import com.example.exchangeratesapp.data.model.LatestModel
import com.example.exchangeratesapp.domain.repository.RatesApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RatesApiUseCaseImpl @Inject constructor(
    private val ratesRepository: RatesApiRepository
) : RatesApiUseCase{

    override suspend fun getLatest(code: String?): Flow<LatestModel> =
        ratesRepository.getLatest(code)
}