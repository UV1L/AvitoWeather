package com.example.data

import com.example.data.retrofit.RetrofitService
import com.example.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(private val retrofitService: RetrofitService) : WeatherRepository {

    override val weatherData: Flow<List<Any>>
        get() = TODO("Not yet implemented")

    override fun getWeather() {
        TODO("Not yet implemented")
    }
}