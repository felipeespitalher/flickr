package com.tigerspike.databinding.adapter

import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.tigerspike.model.Photo

@BindingAdapter("app:imageUrl")
fun setImageUrl(view: SimpleDraweeView, photo: Photo?) {
    photo?.let {
        with(photo) {
            val url = "https://farm$farm.staticflickr.com/$server/${id}_${secret}_n.jpg"
            view.setImageURI(url)
        }
    }
}