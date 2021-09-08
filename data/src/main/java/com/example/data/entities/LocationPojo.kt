package com.example.data.entities

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class LocationPojo(
    @SerializedName("lat") val lat: BigDecimal,
    @SerializedName("lon") val lon: BigDecimal,
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("tz_id") val tzId: String,
    @SerializedName("localtime_epoch") val localtimeEpoch: Int,
    @SerializedName("localtime") val localtime: String
)