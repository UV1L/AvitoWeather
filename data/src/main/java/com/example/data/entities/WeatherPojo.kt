package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class WeatherPojo(
    @SerializedName("location") val location: LocationPojo,
    @SerializedName("current") val current: CurrentPojo,
    @SerializedName("forecast") val forecast: ForecastPojo
)