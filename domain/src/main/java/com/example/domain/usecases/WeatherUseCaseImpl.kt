package com.example.domain.usecases

import com.example.domain.entities.Weather
import com.example.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : WeatherUseCase {

    override suspend fun execute(cityName: String): Flow<Weather> {

        return weatherRepository.getWeather(cityName)
    }
}