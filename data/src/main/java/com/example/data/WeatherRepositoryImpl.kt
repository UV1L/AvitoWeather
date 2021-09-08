package com.example.data

import com.example.data.entities.CurrentPojo
import com.example.data.retrofit.RetrofitService
import com.example.domain.entities.Weather
import com.example.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(private val retrofitService: RetrofitService) : WeatherRepository {

    companion object {

        private const val API_KEY = "72f6fd38310a4e2aae8155022210309"
    }

    override suspend fun getWeather(cityName: String): Flow<Weather>? {

        var weather: Weather? = null

        withContext(Dispatchers.IO) {

            val weatherCall = retrofitService.getWeather(api_key = API_KEY, city_name = cityName)

            val response = weatherCall.execute()

            if (response.isSuccessful)
                weather = WeatherMapper.getWeather(response.body()!!.current)
        }

        weather?.let {

            return flow {

                emit(it)
            }
        } ?: return null
    }
}

private class WeatherMapper {

    companion object {

        fun getWeather(currentPojo: CurrentPojo): Weather =
            Weather(currentPojo.tempC.toFloat())
    }
}