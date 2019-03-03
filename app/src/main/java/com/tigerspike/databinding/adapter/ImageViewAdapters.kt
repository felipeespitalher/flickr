package com.tigerspike.databinding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.tigerspike.R
import com.tigerspike.model.Photo

@BindingAdapter("app:imageUrl")
fun setImageUrl(view: ImageView?, photo: Photo?) {
    photo?.let {
        with(photo) {
            val url = "https://farm$farm.staticflickr.com/$server/${id}_${secret}_n.jpg"
            Picasso
                    .get()
                    .load(url)
                    .into(view)
        }

    }
}