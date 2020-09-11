package com.mo.aad.features.main.data

import com.google.gson.annotations.SerializedName

data class LearningHoursUser(
    @SerializedName("name") val name: String,
    @SerializedName("hours") val hours: Int,
    @SerializedName("country") val country: String,
    @SerializedName("badgeUrl") val badgeUrl: String
)
