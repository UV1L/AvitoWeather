package com.example.domain.repositories

import com.example.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(cityName: String): Flow<Weather>
}