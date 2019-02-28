package com.tigerspike.data.repository

import com.tigerspike.data.FlickrApi
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val flickrApi: FlickrApi
) {

    fun fetchRecentPhotos(): Single<Unit> {
        return flickrApi.fetchRecentPhotos()
    }

}