package com.example.exchangeratesapp.di.module

import com.example.exchangeratesapp.data.repository.RatesApiRepositoryImpl
import com.example.exchangeratesapp.data.repository.RatesDbRepositoryImpl
import com.example.exchangeratesapp.domain.repository.RatesApiRepository
import com.example.exchangeratesapp.domain.repository.RatesDbRepository
import com.example.exchangeratesapp.domain.usecase.RatesApiUseCase
import com.example.exchangeratesapp.domain.usecase.RatesApiUseCaseImpl
import com.example.exchangeratesapp.domain.usecase.RatesDbUseCase
import com.example.exchangeratesapp.domain.usecase.RatesDbUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {
    @Singleton
    @Binds
    fun bindRatesApiRepository(impl: RatesApiRepositoryImpl): RatesApiRepository

    @Singleton
    @Binds
    fun bindRatesDbRepository(impl: RatesDbRepositoryImpl): RatesDbRepository

    @Singleton
    @Binds
    fun bindRatesApiUseCase(impl: RatesApiUseCaseImpl): RatesApiUseCase

    @Singleton
    @Binds
    fun bindRatesDbUseCase(impl: RatesDbUseCaseImpl): RatesDbUseCase
}