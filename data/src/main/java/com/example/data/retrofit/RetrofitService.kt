package com.example.data.retrofit

import com.example.data.entities.WeatherPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("forecast.json")
    fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") cityName: String,
        @Query("days") days: Int = 7,
        @Query("aqi") air_quality: Boolean = false,
        @Query("alerts") alerts: Boolean = false,
        @Query("lang") lang: String = "ru"
    ): Call<WeatherPojo>
}