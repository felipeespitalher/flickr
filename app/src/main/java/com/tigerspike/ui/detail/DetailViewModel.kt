package com.tigerspike.ui.detail

import com.tigerspike.model.Photo
import com.tigerspike.ui.commons.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {

    lateinit var photo: Photo

    fun setup(photo: Photo) {
        this.photo = photo
    }

}