package com.tigerspike.ui.provider

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceProvider @Inject constructor(
        private val context: Context
) {

    fun getString(@StringRes resource: Int): String {
        return context.getString(resource)
    }

    fun getString(@StringRes resource: Int, vararg args: Any): String {
        return context.getString(resource, args)
    }

}