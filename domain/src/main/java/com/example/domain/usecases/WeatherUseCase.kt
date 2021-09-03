package com.example.domain.usecases

import com.example.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {

    fun execute()
}