package com.example.exchangeratesapp.presentation.popularCurrency

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.domain.entity.PopularCurrency
import com.example.exchangeratesapp.domain.usecase.CurrencyApiUseCase
import com.example.exchangeratesapp.domain.usecase.CurrencyDbUseCase
import com.example.exchangeratesapp.ui.CurrencyListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class PopularCurrencyViewModel @Inject constructor(
    private val currencyApiUseCase: CurrencyApiUseCase,
    private val currencyDbUseCase: CurrencyDbUseCase,
) : ViewModel() {

    private var favouriteCurrencyList: List<FavouriteCurrency> = emptyList()

    private var _uiState: MutableStateFlow<CurrencyListUiState> = MutableStateFlow(
        CurrencyListUiState.SuccessCurrency(
            emptyList()
        )
    )

    val uiState: StateFlow<CurrencyListUiState> = _uiState

    fun getSpinnerCode(): String =
        currencyApiUseCase.spinnerCode

    fun getSpinnerArray(): Array<String> =
        currencyApiUseCase.spinnerArray

    fun setSpinnerCode(code: String) {
        currencyApiUseCase.onSetSpinnerCode(code)
    }

    init {
        getFavouriteList()
    }

    fun getCurrencyList(code: String? = null, context: Context) {
        viewModelScope.launch {
            try {
                currencyApiUseCase.getCurrencyList(code)
                    .collect { value ->
                        currencyDbUseCase.deletePopularCurrency()
                        value.results.forEach {
                            currencyDbUseCase.insertPopularCurrency(
                                PopularCurrency(
                                    it.key,
                                    it.value,
                                    favouriteCurrencyList.contains(FavouriteCurrency(it.key))
                                )
                            )
                        }
                        currencyDbUseCase.getPopularCurrency()
                            .collect { value ->
                                _uiState.value = CurrencyListUiState.SuccessCurrency(value)
                            }
                    }

            } catch (http: HttpException) {
                _uiState.value = CurrencyListUiState.Error(
                    context.getString(R.string.textException) + http.code().toString()
                )
                currencyDbUseCase.getPopularCurrency()
                    .collect { value ->
                        _uiState.value = CurrencyListUiState.SuccessCurrency(value)
                    }

            } catch (e: Exception) {
                _uiState.value =
                    CurrencyListUiState.Error(context.getString(R.string.textException))
                currencyDbUseCase.getPopularCurrency()
                    .collect { value ->
                        _uiState.value = CurrencyListUiState.SuccessCurrency(value)
                    }
            }
        }
    }

    fun getCurrencySpinnerArray(context: Context) {
        viewModelScope.launch {
            try {
                if (currencyApiUseCase.spinnerArray.isEmpty())
                    currencyApiUseCase.getCurrencyList(null)
                        .collect { value ->
                            currencyApiUseCase.spinnerArray =
                                value.results.map { it.key }.toTypedArray()
                            _uiState.value =
                                CurrencyListUiState.SuccessSpinner(currencyApiUseCase.spinnerArray)
                        }
                else _uiState.value =
                    CurrencyListUiState.SuccessSpinner(currencyApiUseCase.spinnerArray)
            } catch (http: HttpException) {
                _uiState.value = CurrencyListUiState.Error(
                    context.getString(R.string.textException) + http.code().toString()
                )
            } catch (e: Exception) {
                _uiState.value =
                    CurrencyListUiState.Error(context.getString(R.string.textException))
                currencyDbUseCase.getPopularCurrency()
                    .collect { value ->
                        currencyApiUseCase.spinnerArray =
                            value.map { it.code }.toTypedArray()
                        _uiState.value =
                            CurrencyListUiState.SuccessSpinner(currencyApiUseCase.spinnerArray)
                    }
            }
        }
    }

    private fun getFavouriteList() {
        viewModelScope.launch {
            favouriteCurrencyList = currencyDbUseCase.getFavouriteList()
        }
    }

    fun insertFavouriteCurrency(currency: FavouriteCurrency) {
        viewModelScope.launch {
            currencyDbUseCase.insertFavouriteCurrency(currency)
        }
    }

    fun deleteFavouriteCurrency(currency: FavouriteCurrency) {
        viewModelScope.launch {
            currencyDbUseCase.deleteFavouriteCurrency(currency.code)
        }
    }

    fun updateIsFavouriteCurrency(isFavourite: Boolean, currency: PopularCurrency) {
        viewModelScope.launch {
            currencyDbUseCase.updateIsFavouriteCurrency(isFavourite, currency.code)
        }
    }

}

