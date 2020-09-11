package com.mo.aad.features.main.data

import com.google.gson.annotations.SerializedName

data class SkillsScoreUser(
    @SerializedName("name") val name: String,
    @SerializedName("score") val score: Int,
    @SerializedName("country") val country: String,
    @SerializedName("badgeUrl") val badgeUrl: String
)