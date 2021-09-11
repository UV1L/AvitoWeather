package com.example.domain.usecases

import com.example.domain.entities.Weather
import com.example.domain.repositories.WeatherRepository
import com.example.extensions.Extensions.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {

    suspend fun execute(cityName: String): Flow<Resource<Weather>>
}