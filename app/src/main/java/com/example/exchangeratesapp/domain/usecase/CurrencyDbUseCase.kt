package com.example.exchangeratesapp.domain.usecase

import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.domain.entity.PopularCurrency
import kotlinx.coroutines.flow.Flow

interface CurrencyDbUseCase {
    var codeSort: Int?

    var valueSort: Int?

    fun onSetCodeSort(sort: Int?)

    fun onSetValueSort(sort: Int?)

    suspend fun insertPopularCurrency(currency: PopularCurrency)

    suspend fun deletePopularCurrency()

    suspend fun updateIsFavouriteCurrency(isFavourite: Boolean, code: String)

    suspend fun getPopularCurrency(): Flow<List<PopularCurrency>>

    suspend fun insertFavouriteCurrency(currency: FavouriteCurrency)

    suspend fun deleteFavouriteCurrency(code: String)

    suspend fun getFavouriteList(): List<FavouriteCurrency>

    suspend fun getFavouriteCurrency(): Flow<List<PopularCurrency>>
}