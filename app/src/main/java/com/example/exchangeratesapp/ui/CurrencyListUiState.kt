package com.example.exchangeratesapp.ui

import com.example.exchangeratesapp.domain.entity.PopularCurrency

sealed class CurrencyListUiState {
    data class SuccessSpinner(var codeList: Array<String>) : CurrencyListUiState()
    data class SuccessCurrency(var currencyList: List<PopularCurrency>) : CurrencyListUiState()
    data class Error(var exception: String) : CurrencyListUiState()
}