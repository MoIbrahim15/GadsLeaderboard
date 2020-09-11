package com.mo.aad.features.main.remote

import com.mo.aad.features.main.data.SkillsScoreUser
import retrofit2.http.GET

interface SkillIQAPIService {
    @GET("/api/skilliq")
    suspend fun getTopSkillsUsers() : ArrayList<SkillsScoreUser>
}