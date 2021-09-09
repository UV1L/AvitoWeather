package com.example.data.entities

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class DayWeatherPojo (
    @SerializedName("avgtemp_c") val avgTempC: BigDecimal,
    @SerializedName("condition") val condition: ConditionPojo
        )