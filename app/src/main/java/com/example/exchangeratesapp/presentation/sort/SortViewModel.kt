package com.example.exchangeratesapp.presentation.sort

import androidx.lifecycle.ViewModel
import com.example.exchangeratesapp.domain.usecase.CurrencyDbUseCase
import javax.inject.Inject

class SortViewModel @Inject constructor(
    private val currencyDbUseCase: CurrencyDbUseCase,
) : ViewModel() {

    fun setCodeSort(sort: Int?) {
        currencyDbUseCase.onSetCodeSort(sort)
    }

    fun setValueSort(sort: Int?) {
        currencyDbUseCase.onSetValueSort(sort)
    }

    fun getCodeSort(): Int? =
        currencyDbUseCase.codeSort

    fun getValueSort(): Int? =
        currencyDbUseCase.valueSort
}