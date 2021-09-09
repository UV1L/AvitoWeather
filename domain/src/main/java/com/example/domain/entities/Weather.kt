package com.example.domain.entities

data class Weather(
    val date: String,
    val tempC: Float,
    val forecast: List<Float>,
)