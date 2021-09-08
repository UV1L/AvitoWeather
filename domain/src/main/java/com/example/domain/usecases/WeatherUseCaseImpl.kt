package com.example.domain.usecases

import com.example.domain.entities.Weather
import com.example.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : WeatherUseCase {

    override var data: Flow<Weather>? = null

    override suspend fun execute(cityName: String) {

        val weather = withContext(Dispatchers.IO) {

            weatherRepository.getWeather(cityName)
        }

        weather?.let {
            data = weather
        }
    }
}