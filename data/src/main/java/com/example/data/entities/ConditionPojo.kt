package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class ConditionPojo(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val iconPath: String,
    @SerializedName("code") val code: Int
)