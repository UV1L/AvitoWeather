package com.example.avitoweather.vm

import androidx.lifecycle.*
import com.example.domain.entities.Weather
import com.example.domain.usecases.WeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel() {

    val weather: LiveData<Weather>
    get() =
        if (weatherUseCase.data != null)
            weatherUseCase.data!!.asLiveData()
        else
            liveData { }

    fun loadWeather(cityName: String) {

        viewModelScope.launch {

            weatherUseCase.execute(cityName)
        }
    }
}