package com.tigerspike.data.repository

import com.tigerspike.data.FlickrApi
import com.tigerspike.data.mapper.PhotoMapper
import com.tigerspike.model.Photo
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val flickrApi: FlickrApi
) {

    fun fetchRecentPhotos(): Single<List<Photo>> {
        return flickrApi
            .fetchRecentPhotos()
            .map {
                PhotoMapper.toPhoto(it)
            }
    }

}