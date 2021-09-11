package com.example.domain.repositories

import com.example.domain.entities.Weather
import com.example.extensions.Extensions.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(cityName: String): Flow<Resource<Weather>>
}