package com.example.avitoweather.di

import com.example.data.WeatherRepositoryImpl
import com.example.data.retrofit.RetrofitService
import com.example.domain.repositories.WeatherRepository
import com.example.domain.usecases.WeatherUseCase
import com.example.domain.usecases.WeatherUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainDaggerModule {

    companion object {

        const val BASE_URL = "https://api.weatherapi.com/v1/"
    }

    @Provides
    fun provideWeatherUseCase(weatherRepository: WeatherRepository): WeatherUseCase =
        WeatherUseCaseImpl(weatherRepository)

    @Provides
    fun provideWeatherRepository(retrofitService: RetrofitService): WeatherRepository =
        WeatherRepositoryImpl(retrofitService)

    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService =
        retrofit.create(RetrofitService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}