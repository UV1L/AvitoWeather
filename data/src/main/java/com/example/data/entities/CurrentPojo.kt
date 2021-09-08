package com.example.data.entities

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CurrentPojo(
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: String,
    @SerializedName("temp_c") val tempC: BigDecimal,
    @SerializedName("temp_f") val tempF: BigDecimal,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: ConditionPojo,
    @SerializedName("wind_mph") val windMph: BigDecimal,
    @SerializedName("wind_kph") val windKph: BigDecimal,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: BigDecimal,
    @SerializedName("pressure_in") val pressureIn: BigDecimal,
    @SerializedName("precip_mm") val precipMm: BigDecimal,
    @SerializedName("precip_in") val precipIn: BigDecimal,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeC: BigDecimal,
    @SerializedName("feelslike_f") val feelsLikeF: BigDecimal,
    @SerializedName("vis_km") val visKm: BigDecimal,
    @SerializedName("vis_miles") val visMiles: BigDecimal,
    @SerializedName("uv") val uv: BigDecimal,
    @SerializedName("gust_mph") val gustMph: BigDecimal,
    @SerializedName("gust_kph") val gustKph: BigDecimal
)