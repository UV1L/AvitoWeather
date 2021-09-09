package com.example.avitoweather.vm

import androidx.lifecycle.*
import com.example.domain.entities.Weather
import com.example.domain.usecases.WeatherUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first

class WeatherViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel() {

    private val _weather: MutableLiveData<Weather> = MutableLiveData()
    val weather: LiveData<Weather> = _weather

    fun loadWeather(cityName: String) {

        viewModelScope.launch {

            val tempFlow = weatherUseCase.execute(cityName)

            try {
                _weather.postValue(tempFlow.first())
            }
            catch (e: NoSuchElementException) {
                print("Нет интернета!")
            }
        }
    }
}