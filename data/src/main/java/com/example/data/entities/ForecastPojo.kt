package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class ForecastPojo (
    @SerializedName("forecastday") val forecastDay: List<ForecastDayPojo>
        )