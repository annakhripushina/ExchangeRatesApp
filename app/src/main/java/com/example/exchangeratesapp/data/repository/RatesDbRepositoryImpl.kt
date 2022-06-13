package com.example.exchangeratesapp.data.repository

import com.example.exchangeratesapp.data.network.ApiService
import com.example.exchangeratesapp.data.room.RatesDao
import com.example.exchangeratesapp.domain.entity.FavouriteRate
import com.example.exchangeratesapp.domain.entity.PopularRate
import com.example.exchangeratesapp.domain.repository.RatesDbRepository
import javax.inject.Inject

class RatesDbRepositoryImpl @Inject constructor(private val ratesDao: RatesDao) : RatesDbRepository {
    override suspend fun insertPopularRate(rate: PopularRate) {
        ratesDao.insertPopularRate(rate)
    }

    override suspend fun deletePopularRates() {
        ratesDao.deletePopularRates()
    }

    override suspend fun insertFavouriteRate(rate: FavouriteRate) {
        ratesDao.insertFavouriteRate(rate)
    }

    override suspend fun deleteFavouriteRates(code: String) {
        ratesDao.deleteFavouriteRates(code)
    }

}