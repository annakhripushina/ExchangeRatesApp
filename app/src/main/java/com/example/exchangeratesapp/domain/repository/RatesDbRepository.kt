package com.example.exchangeratesapp.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exchangeratesapp.domain.entity.FavouriteRate
import com.example.exchangeratesapp.domain.entity.PopularRate

interface RatesDbRepository {
    suspend fun insertPopularRate(rate: PopularRate)

    suspend fun deletePopularRates()

    suspend fun insertFavouriteRate(rate: FavouriteRate)

    suspend fun deleteFavouriteRates(code: String)
}