package com.example.exchangeratesapp.data.repository

import com.example.exchangeratesapp.data.room.CurrencyDao
import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.domain.entity.PopularCurrency
import com.example.exchangeratesapp.domain.repository.CurrencyDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbRepositoryImpl @Inject constructor(private val currencyDao: CurrencyDao) :
    CurrencyDbRepository {

    override suspend fun insertPopularCurrency(currency: PopularCurrency) {
        currencyDao.insertPopularCurrency(currency)
    }

    override suspend fun deletePopularCurrency() {
        currencyDao.deletePopularCurrency()
    }

    override suspend fun updateIsFavouriteCurrency(isFavourite: Boolean, code: String) {
        currencyDao.updateIsFavouriteCurrency(isFavourite, code)
    }

    override suspend fun getPopularCurrency(
        codeSort: Int?,
        valueSort: Int?
    ): Flow<List<PopularCurrency>> =
        currencyDao.getPopularCurrency(codeSort, valueSort)

    override suspend fun insertFavouriteCurrency(currency: FavouriteCurrency) {
        currencyDao.insertFavouriteCurrency(currency)
    }

    override suspend fun deleteFavouriteCurrency(code: String) {
        currencyDao.deleteFavouriteCurrency(code)
    }

    override suspend fun getFavouriteList(): List<FavouriteCurrency> =
        currencyDao.getFavouriteList()

    override suspend fun getFavouriteCurrency(
        codeSort: Int?,
        valueSort: Int?
    ): Flow<List<PopularCurrency>> =
        currencyDao.getFavouriteCurrency(codeSort, valueSort)

}