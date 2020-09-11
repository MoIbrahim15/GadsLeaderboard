package com.mo.aad.koin

import com.mo.aad.features.main.repository.MainRepository
import com.mo.aad.features.main.viewmodel.MainViewModel
import com.mo.aad.features.submission.repository.SubmissionRepository
import com.mo.aad.features.submission.viewmodel.SubmissionViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
val uiModule = module {
    single { MainRepository(get(), get()) }
    viewModel { MainViewModel(get()) }

    single { SubmissionRepository(get()) }
    viewModel { SubmissionViewModel(get()) }
}