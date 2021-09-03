package com.example.data.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("current.json")
    fun getWeather(
        @Query("key") api_key: String,
        @Query("q") city_name: String,
        @Query("aqi") air_quality: Boolean = false
    ): Flow<List<Any>>
}