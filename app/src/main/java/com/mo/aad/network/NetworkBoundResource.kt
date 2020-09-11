package com.mo.aad.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

const val NETWORK_ERROR = "Network error"
const val NETWORK_ERROR_TIMEOUT = "Network timeout"
const val UNKNOWN_ERROR = "Unknown error"

@FlowPreview
@ExperimentalCoroutinesApi
fun <Result> networkBoundResource(
    fetch: suspend () -> Result,
) = flow<Resource<Result>> {

    emit(Resource.loading(null))
    try {
        emit(Resource.success(fetch.invoke()))
    } catch (throwable: Throwable) {
        when (throwable) {
            is TimeoutCancellationException -> {
                emit(Resource.error(NETWORK_ERROR_TIMEOUT, null))
            }
            is IOException -> {
                emit(Resource.error(NETWORK_ERROR, null))
            }
            is HttpException -> {
                emit(Resource.error(convertErrorBody(throwable), null))
            }
            else -> {
                emit(Resource.error(UNKNOWN_ERROR, null))
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String {
    return try {
        throwable.response()?.errorBody()?.string() ?: UNKNOWN_ERROR
    } catch (exception: Exception) {
        UNKNOWN_ERROR
    }
}