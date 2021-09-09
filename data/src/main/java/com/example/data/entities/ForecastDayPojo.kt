package com.example.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class ForecastDayPojo (
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Int,
    @SerializedName("day") val day: DayWeatherPojo
        )