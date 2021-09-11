package com.example.avitoweather.vm

import androidx.lifecycle.*
import com.example.domain.entities.Weather
import com.example.domain.usecases.WeatherUseCase
import com.example.extensions.Extensions.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class WeatherViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel() {

    private val _weather: MutableLiveData<Weather> = MutableLiveData()
    private val _exceptions: MutableLiveData<String> = MutableLiveData()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val weather: LiveData<Weather> = _weather
    val exceptions: LiveData<String> = _exceptions
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadWeather(cityName: String) {

        _exceptions.postValue("")
        _isLoading.postValue(false)

        viewModelScope.launch {

            val tempFlow = weatherUseCase.execute(cityName)

            tempFlow.collect { result ->

                when (result) {

                    is Resource.Success -> {
                        _weather.postValue(result.data)
                    }

                    is Resource.Error -> {
                        _exceptions.postValue(result.message)
                    }

                    is Resource.Loading -> {
                        _isLoading.postValue(true)
                    }
                }
            }
        }
    }

    fun unsetLoading() {

        _isLoading.value = false
    }
}