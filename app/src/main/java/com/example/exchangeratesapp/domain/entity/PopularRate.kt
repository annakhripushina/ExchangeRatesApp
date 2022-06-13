package com.example.exchangeratesapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popularRateTable")
data class PopularRate (
    @PrimaryKey
    val code: String,
    val value: Double,
)
