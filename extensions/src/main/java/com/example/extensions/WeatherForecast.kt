package com.example.extensions

class WeatherForecast(private val pair: Pair<Float, String>) {

    val tempC = pair.first
    val description = pair.second
}