package com.example.domain.usecases

import com.example.domain.entities.Weather
import com.example.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {

    suspend fun execute(cityName: String): Flow<Weather>
}