package com.example.data

import com.example.data.entities.WeatherPojo
import com.example.data.retrofit.RetrofitService
import com.example.domain.entities.Weather
import com.example.domain.repositories.WeatherRepository
import com.example.extensions.Extensions.Resource
import com.example.extensions.WeatherForecast
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class WeatherRepositoryImpl(private val retrofitService: RetrofitService) : WeatherRepository {

    companion object {

        private const val API_KEY = "72f6fd38310a4e2aae8155022210309"
    }

    override suspend fun getWeather(cityName: String): Flow<Resource<Weather>> = flow {

        emit(Resource.Loading<Weather>())

        val response = withContext(IO) {

            val weatherCall = retrofitService.getWeather(apiKey = API_KEY, cityName = cityName)

            runCatching {
                weatherCall.execute()
            }.getOrNull()
        }

        try {
            val weather = WeatherMapper.getWeather(response!!.body()!!)
            emit(Resource.Success<Weather>(weather))
        } catch (e: HttpException) {
            emit(Resource.Error<Weather>(e.localizedMessage ?: "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error<Weather>("Couldn't reach server"))
        } catch (e: Throwable) {
            emit(Resource.Error<Weather>("Couldn't reach server"))
        }
    }

//    override suspend fun getWeather(cityName: String): Flow<Resource<Weather>> = flowOf(Resource.Loading<Weather>())
//        .map { retrofitService.getWeather(apiKey = API_KEY, cityName = cityName) }
//        .map {
//
//            try {
//                val weather = WeatherMapper.getWeather(response!!.body()!!)
//                Resource.Success<Weather>(weather)
//            } catch (e: HttpException) {
//                Resource.Error<Weather>(e.localizedMessage ?: "Something went wrong")
//            } catch (e: IOException) {
//                Resource.Error<Weather>("Couldn't reach server")
//            } catch (e: Throwable) {
//                Resource.Error<Weather>("Couldn't reach server")
//            }
//        }
//        .flowOn(IO)
//    }
}

private class WeatherMapper {

    companion object {

        fun getWeather(weatherPojo: WeatherPojo): Weather {

            val forecast = mutableListOf<WeatherForecast>()

            weatherPojo.forecast.forecastDay.forEach {
                forecast.add(
                    WeatherForecast(Pair(it.day.avgTempC.toFloat(), it.day.condition.text))
                )
            }

            return Weather(
                weatherPojo.forecast.forecastDay.first().date,
                weatherPojo.current.tempC.toFloat(),
                weatherPojo.current.condition.text,
                forecast
            )
        }
    }
}