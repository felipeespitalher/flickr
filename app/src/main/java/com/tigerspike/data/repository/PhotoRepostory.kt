package com.tigerspike.data.repository

import com.tigerspike.data.FlickrApi
import io.reactivex.Completable
import javax.inject.Inject

class PhotoRepostory @Inject constructor(
    private val flickrApi: FlickrApi
) {

    fun fetchRecentPhotos(): Completable {
        return flickrApi.fetchRecentPhotos()
    }

}