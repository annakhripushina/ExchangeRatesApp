package com.example.exchangeratesapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteRateTable")
data class FavouriteRate (
    @PrimaryKey
    val code: String
)