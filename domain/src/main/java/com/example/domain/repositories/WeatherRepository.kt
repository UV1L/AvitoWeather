package com.example.domain.repositories

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    val weatherData: Flow<List<Any>>

    fun getWeather()
}