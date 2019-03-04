package com.tigerspike.extension

import com.tigerspike.R
import com.tigerspike.data.output.ErrorOutput
import retrofit2.HttpException

fun Throwable.getApiError(): ErrorOutput {
    val apiError = if (this is HttpException) {
        ErrorOutput(R.string.network_error_http)
    } else {
        ErrorOutput(R.string.network_error_internet)
    }
    return apiError.also {
        it.rootException = this
    }
}