package com.tigerspike.databinding.adapter

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun setImageUrl(view: ImageView?, url: String?) {
    if (url != null && !TextUtils.isEmpty(url) && view != null) {
        Picasso.get().load(url)
            .into(view)
    }
}