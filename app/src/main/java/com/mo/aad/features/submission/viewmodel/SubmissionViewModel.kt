package com.mo.aad.features.submission.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mo.aad.features.submission.repository.SubmissionRepository
import com.mo.aad.network.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@FlowPreview
@ExperimentalCoroutinesApi
class SubmissionViewModel(val repo: SubmissionRepository) : ViewModel() {

    val submissionLiveData: MutableLiveData<Resource<Unit>> = MutableLiveData()

    fun projectSubmission(
        firstName: String,
        lastName: String,
        email: String,
        link: String
    ) {
        repo.projectSubmission(firstName, lastName, email, link).onEach {
            submissionLiveData.value = it
        }.launchIn(viewModelScope)
    }

}