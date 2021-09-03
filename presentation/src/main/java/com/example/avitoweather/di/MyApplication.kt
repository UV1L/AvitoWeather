package com.example.avitoweather.di

import android.app.Application
import com.example.domain.usecases.WeatherUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainDaggerModule::class])
interface ApplicationComponent {

    fun inject(): WeatherUseCase
}

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent =
            DaggerApplicationComponent
                .builder()
                .build()
    }
}