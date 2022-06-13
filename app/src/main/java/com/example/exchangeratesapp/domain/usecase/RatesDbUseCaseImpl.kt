package com.example.exchangeratesapp.domain.usecase

import com.example.exchangeratesapp.domain.entity.FavouriteRate
import com.example.exchangeratesapp.domain.entity.PopularRate
import com.example.exchangeratesapp.domain.repository.RatesDbRepository
import javax.inject.Inject

class RatesDbUseCaseImpl @Inject constructor(private val ratesDbRepository: RatesDbRepository): RatesDbUseCase {
    override suspend fun insertPopularRate(rate: PopularRate) {
        ratesDbRepository.insertPopularRate(rate)
    }

    override suspend fun deletePopularRates() {
        ratesDbRepository.deletePopularRates()
    }

    override suspend fun insertFavouriteRate(rate: FavouriteRate) {
        ratesDbRepository.insertFavouriteRate(rate)
    }

    override suspend fun deleteFavouriteRates(code: String) {
        ratesDbRepository.deleteFavouriteRates(code)
    }
}