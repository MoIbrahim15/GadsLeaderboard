package com.mo.aad.features.main.repository

import com.mo.aad.features.main.data.LearningHoursUser
import com.mo.aad.features.main.data.SkillsScoreUser
import com.mo.aad.features.main.remote.LearningAPIService
import com.mo.aad.features.main.remote.SkillIQAPIService
import com.mo.aad.network.Resource
import com.mo.aad.network.networkBoundResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
@FlowPreview
class MainRepository(
    private val skillIQAPIService: SkillIQAPIService,
    private val learningAPIService: LearningAPIService
) {

    fun getTopLearnerUsers(): Flow<Resource<List<LearningHoursUser>>> {
        return networkBoundResource(
            fetch = { learningAPIService.getTopLearnerUsers() },
        )
    }

    fun getTopSkillsUsers(): Flow<Resource<List<SkillsScoreUser>>> {
        return networkBoundResource(
            fetch = { skillIQAPIService.getTopSkillsUsers() },
        )
    }
}