package com.tigerspike.data.mapper

import com.tigerspike.data.output.PhotoWrapperOutput
import com.tigerspike.model.Photo

object PhotoMapper {

    fun toPhoto(wrapper: PhotoWrapperOutput): List<Photo> {
        return wrapper.photo.map {
            with(it) {
                Photo(
                    farm,
                    server,
                    id,
                    secret,
                    title
                )
            }
        }
    }

}