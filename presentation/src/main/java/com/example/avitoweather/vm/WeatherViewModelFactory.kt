package com.example.avitoweather.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.WeatherUseCase

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(private val useCase: WeatherUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        WeatherViewModel(useCase) as T
}