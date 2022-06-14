package com.example.exchangeratesapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popularCurrencyTable")
data class PopularCurrency(
    @PrimaryKey
    val code: String,
    val value: Double,
    var isFavourite: Boolean = false
)
