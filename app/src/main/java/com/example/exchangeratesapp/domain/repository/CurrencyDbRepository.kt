package com.example.exchangeratesapp.domain.repository

import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.domain.entity.PopularCurrency
import kotlinx.coroutines.flow.Flow

interface CurrencyDbRepository {

    suspend fun insertPopularCurrency(currency: PopularCurrency)

    suspend fun deletePopularCurrency()

    suspend fun updateIsFavouriteCurrency(isFavourite: Boolean, code: String)

    suspend fun getPopularCurrency(codeSort: Int?, valueSort: Int?): Flow<List<PopularCurrency>>

    suspend fun insertFavouriteCurrency(currency: FavouriteCurrency)

    suspend fun deleteFavouriteCurrency(code: String)

    suspend fun getFavouriteList(): List<FavouriteCurrency>

    suspend fun getFavouriteCurrency(
        codeSort: Int?,
        valueSort: Int?
    ): Flow<List<PopularCurrency>>

}