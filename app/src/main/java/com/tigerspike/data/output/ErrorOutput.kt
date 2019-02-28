package com.tigerspike.data.output

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.net.UnknownHostException

@JsonClass(generateAdapter = true)
data class ErrorOutput(
    var message: String,
    var rootException: Throwable? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ErrorOutput

        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        return message.hashCode()
    }

    fun isConnectivityError() = rootException is UnknownHostException
}

fun Throwable.getApiError(): ErrorOutput {
    val apiError = if (this is HttpException) {
        val body = response().errorBody()

        try {
            body?.let {
                val error = Moshi.Builder().build().adapter(ErrorOutput::class.java).fromJson(it.string())
                return error ?: ErrorOutput("DEFAULT ERROR")
            } ?: ErrorOutput("DEFAULT ERROR")
        } catch (exception: Exception) {
            ErrorOutput("DEFAULT ERROR")
        }
    } else {
        ErrorOutput("DEFAULT ERROR")
    }

    return apiError.also {
        it.rootException = this
    }
}