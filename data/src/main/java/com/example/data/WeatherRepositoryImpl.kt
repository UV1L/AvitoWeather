package com.example.data

import com.example.data.entities.CurrentPojo
import com.example.data.entities.WeatherPojo
import com.example.data.retrofit.RetrofitService
import com.example.domain.entities.Weather
import com.example.domain.repositories.WeatherRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(private val retrofitService: RetrofitService) : WeatherRepository {

    companion object {

        private const val API_KEY = "72f6fd38310a4e2aae8155022210309"
    }

    override suspend fun getWeather(cityName: String): Flow<Weather> {

        val weatherDeferred = CoroutineScope(Dispatchers.IO).async {

            val weatherCall = retrofitService.getWeather(apiKey = API_KEY, cityName = cityName)

            val response =
                runCatching {
                    weatherCall.execute()
                }.getOrNull()

            return@async if (response?.isSuccessful == true)
                flow {

                    emit(
                        WeatherMapper.getWeather(response.body()!!)
                    )
                }
            else flow {}
        }

        return weatherDeferred.await()
    }
}

private class WeatherMapper {

    companion object {

        fun getWeather(weatherPojo: WeatherPojo): Weather {

            val forecastTempC = mutableListOf<Float>()

            weatherPojo.forecast.forecastDay.forEach {
                forecastTempC.add(it.day.avgTempC.toFloat())
            }

            return Weather(
                weatherPojo.forecast.forecastDay.first().date,
                weatherPojo.current.tempC.toFloat(),
                forecastTempC
            )
        }
    }
}