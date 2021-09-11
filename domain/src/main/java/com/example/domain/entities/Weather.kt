package com.example.domain.entities

import com.example.extensions.WeatherForecast

data class Weather(
    val date: String,
    val tempC: Float,
    val description: String,
    val forecast: List<WeatherForecast>
)