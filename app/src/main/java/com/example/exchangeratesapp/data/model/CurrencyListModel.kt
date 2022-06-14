package com.example.exchangeratesapp.data.model

data class CurrencyListModel(
    val base: String,
    val results: HashMap<String, Double>,
    val updated: String,
    val ms: Int,
)