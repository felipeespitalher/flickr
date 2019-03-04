package com.tigerspike.ui.detail

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import com.tigerspike.R
import com.tigerspike.model.Photo
import com.tigerspike.ui.commons.BaseViewModel
import com.tigerspike.ui.commons.SingleEventLiveData
import com.tigerspike.ui.provider.ResourceProvider
import javax.inject.Inject


class DetailViewModel @Inject constructor(
        private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    val share = SingleEventLiveData<Unit>()
    val photo = ObservableField<Photo>()
    val title = ObservableField<String>()
    val farm = ObservableField<SpannableStringBuilder>()
    val id = ObservableField<SpannableStringBuilder>()
    val server = ObservableField<SpannableStringBuilder>()
    val secret = ObservableField<SpannableStringBuilder>()

    fun setup(photo: Photo) {
        this.photo.set(photo)
        title.set(photo.title)
        farm.set(formatInformation(R.string.activity_detail_farm, photo.farm))
        id.set(formatInformation(R.string.activity_detail_id, photo.id))
        server.set(formatInformation(R.string.activity_detail_server, photo.server))
        secret.set(formatInformation(R.string.activity_detail_secret, photo.secret))
    }

    private fun formatInformation(@StringRes resource: Int, value: String): SpannableStringBuilder {
        val boldSpan = StyleSpan(Typeface.BOLD)
        val builder = SpannableStringBuilder()
        builder.append(resourceProvider.getString(resource))
        builder.append(value, boldSpan, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return builder
    }

    fun onSharedPressed() {
        share.set(Unit)
    }

}