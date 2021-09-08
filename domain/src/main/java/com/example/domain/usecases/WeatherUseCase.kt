package com.example.domain.usecases

import com.example.domain.entities.Weather
import com.example.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {

    var data: Flow<Weather>?

    suspend fun execute(cityName: String)
}