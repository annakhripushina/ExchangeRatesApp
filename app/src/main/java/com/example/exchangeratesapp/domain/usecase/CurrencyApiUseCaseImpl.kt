package com.example.exchangeratesapp.domain.usecase

import com.example.exchangeratesapp.data.model.CurrencyListModel
import com.example.exchangeratesapp.domain.repository.CurrencyApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyApiUseCaseImpl @Inject constructor(
    private val currencyRepository: CurrencyApiRepository
) : CurrencyApiUseCase {

    override var spinnerCode: String = "USD"

    override var spinnerArray: Array<String> = emptyArray()

    override fun onSetSpinnerCode(code: String) {
        spinnerCode = code
    }

    override suspend fun getCurrencyList(code: String?): Flow<CurrencyListModel> =
        currencyRepository.getCurrencyList(code)
}