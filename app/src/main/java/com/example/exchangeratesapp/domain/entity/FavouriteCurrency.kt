package com.example.exchangeratesapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteCurrencyTable")
data class FavouriteCurrency(
    @PrimaryKey
    val code: String
)