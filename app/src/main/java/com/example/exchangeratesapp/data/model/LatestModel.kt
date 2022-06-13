package com.example.exchangeratesapp.data.model

data class LatestModel (
    val base : String,
    val results: HashMap<String, Double>,
    val updated: String,
    val ms: Int,
)