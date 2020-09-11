package com.mo.aad.features.submission.repository

import com.mo.aad.features.submission.remote.SubmissionApiService
import com.mo.aad.network.Resource
import com.mo.aad.network.networkBoundResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
@FlowPreview
class SubmissionRepository(
    private val submissionApiService: SubmissionApiService
) {
    fun projectSubmission(
        firstName: String,
        lastName: String,
        email: String,
        link: String
    ): Flow<Resource<Unit>> {
        return networkBoundResource(
            fetch = { submissionApiService.submit(firstName, lastName, email, link) },
        )
    }
}