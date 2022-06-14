package com.example.exchangeratesapp.domain.usecase

import com.example.exchangeratesapp.data.model.CurrencyListModel
import kotlinx.coroutines.flow.Flow

interface CurrencyApiUseCase {

    var spinnerCode: String

    var spinnerArray: Array<String>

    fun onSetSpinnerCode(code: String)

    suspend fun getCurrencyList(code: String?): Flow<CurrencyListModel>

}