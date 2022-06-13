package com.example.exchangeratesapp.presentation.popularRates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratesapp.data.model.LatestModel
import com.example.exchangeratesapp.domain.entity.PopularRate
import com.example.exchangeratesapp.domain.usecase.RatesApiUseCase
import com.example.exchangeratesapp.domain.usecase.RatesDbUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularRatesViewModel @Inject constructor(
    private val ratesApiUseCase: RatesApiUseCase,
    private val ratesDbUseCase: RatesDbUseCase,
    ) : ViewModel() {
//    private lateinit var _ratesList : MutableStateFlow<LatestModel>
//    val ratesList: StateFlow<LatestModel> = _ratesList

    private var _uiState : MutableStateFlow<LatestRatesUiState> = MutableStateFlow(LatestRatesUiState.Success(LatestModel("",HashMap(),"",0)
    ))
    val uiState: StateFlow<LatestRatesUiState> = _uiState

//    private var _spinnerState = MutableStateFlow(LatestRatesUiState.SpinnerSuccess(LatestModel("",HashMap(),"",0)
//    ))
//    val spinnerState: StateFlow<LatestRatesUiState> = _spinnerState

    init {
//        viewModelScope.launch {
//            ratesUseCase.getLatest()
//                .collect { value ->
//                    _uiState.value = LatestRatesUiState.Success(value)
//                }
//        }
        getLatest()
    }

    fun getLatest(code: String? = null){
        viewModelScope.launch {
            ratesApiUseCase.getLatest(code)
                .collect { value ->
                    _uiState.value = LatestRatesUiState.Success(value)
                    ratesDbUseCase.deletePopularRates()
                    value.results.forEach{
                        ratesDbUseCase.insertPopularRate(PopularRate(it.key, it.value))
                    }
                }
        }
    }

    fun getSpinnerArray(){
        viewModelScope.launch {
            ratesApiUseCase.getLatest(null)
                .collect { value ->
                    _uiState.value = LatestRatesUiState.SpinnerSuccess(value)
                }
        }
    }

}

sealed class LatestRatesUiState {
    data class SpinnerSuccess(var codeList: LatestModel): LatestRatesUiState()
    data class Success(var ratesList: LatestModel): LatestRatesUiState()
    data class Error(var exception: Throwable): LatestRatesUiState()
}