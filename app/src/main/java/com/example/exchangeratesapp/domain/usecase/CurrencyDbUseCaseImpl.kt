package com.example.exchangeratesapp.domain.usecase

import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.domain.entity.PopularCurrency
import com.example.exchangeratesapp.domain.repository.CurrencyDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbUseCaseImpl @Inject constructor(
    private val currencyDbRepository: CurrencyDbRepository,
) :
    CurrencyDbUseCase {

    /** Сортировка валюты по алфавиту.
     * 1 - по возрастанию
     * 2 - по убыванию
     */
    override var codeSort: Int? = null

    /** Сортировка валюты по значению.
     * 1 - по возрастанию
     * 2 - по убыванию
     */
    override var valueSort: Int? = null
    override fun onSetCodeSort(sort: Int?) {
        codeSort = sort
    }

    override fun onSetValueSort(sort: Int?) {
        valueSort = sort
    }

    override suspend fun insertPopularCurrency(currency: PopularCurrency) {
        currencyDbRepository.insertPopularCurrency(currency)
    }

    override suspend fun deletePopularCurrency() {
        currencyDbRepository.deletePopularCurrency()
    }

    override suspend fun updateIsFavouriteCurrency(isFavourite: Boolean, code: String) {
        currencyDbRepository.updateIsFavouriteCurrency(isFavourite, code)
    }

    override suspend fun getPopularCurrency(): Flow<List<PopularCurrency>> =
        currencyDbRepository.getPopularCurrency(codeSort, valueSort)

    override suspend fun insertFavouriteCurrency(currency: FavouriteCurrency) {
        currencyDbRepository.insertFavouriteCurrency(currency)
    }

    override suspend fun deleteFavouriteCurrency(code: String) {
        currencyDbRepository.deleteFavouriteCurrency(code)
    }

    override suspend fun getFavouriteList(): List<FavouriteCurrency> =
        currencyDbRepository.getFavouriteList()

    override suspend fun getFavouriteCurrency(): Flow<List<PopularCurrency>> =
        currencyDbRepository.getFavouriteCurrency(codeSort, valueSort)

}