package com.example.exchangeratesapp.domain.usecase

import com.example.exchangeratesapp.domain.entity.FavouriteRate
import com.example.exchangeratesapp.domain.entity.PopularRate

interface RatesDbUseCase {
    suspend fun insertPopularRate(rate: PopularRate)

    suspend fun deletePopularRates()

    suspend fun insertFavouriteRate(rate: FavouriteRate)

    suspend fun deleteFavouriteRates(code: String)
}