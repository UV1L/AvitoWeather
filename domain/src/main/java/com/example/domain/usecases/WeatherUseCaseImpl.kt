package com.example.domain.usecases

import com.example.domain.repositories.WeatherRepository

class WeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : WeatherUseCase {

    override fun execute() {
        TODO("Not yet implemented")
    }
}