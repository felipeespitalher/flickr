package com.tigerspike.data.output

import androidx.annotation.StringRes
import com.squareup.moshi.JsonClass
import java.net.UnknownHostException

@JsonClass(generateAdapter = true)
data class ErrorOutput(
        @StringRes
        var message: Int,
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