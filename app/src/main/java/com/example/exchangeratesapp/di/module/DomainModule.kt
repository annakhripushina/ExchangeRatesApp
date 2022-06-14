package com.example.exchangeratesapp.di.module

import com.example.exchangeratesapp.data.repository.CurrencyApiRepositoryImpl
import com.example.exchangeratesapp.data.repository.CurrencyDbRepositoryImpl
import com.example.exchangeratesapp.domain.repository.CurrencyApiRepository
import com.example.exchangeratesapp.domain.repository.CurrencyDbRepository
import com.example.exchangeratesapp.domain.usecase.CurrencyApiUseCase
import com.example.exchangeratesapp.domain.usecase.CurrencyApiUseCaseImpl
import com.example.exchangeratesapp.domain.usecase.CurrencyDbUseCase
import com.example.exchangeratesapp.domain.usecase.CurrencyDbUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {
    @Singleton
    @Binds
    fun bindCurrencyApiRepository(impl: CurrencyApiRepositoryImpl): CurrencyApiRepository

    @Singleton
    @Binds
    fun bindCurrencyDbRepository(impl: CurrencyDbRepositoryImpl): CurrencyDbRepository

    @Singleton
    @Binds
    fun bindCurrencyApiUseCase(impl: CurrencyApiUseCaseImpl): CurrencyApiUseCase

    @Singleton
    @Binds
    fun bindCurrencyDbUseCase(impl: CurrencyDbUseCaseImpl): CurrencyDbUseCase
}