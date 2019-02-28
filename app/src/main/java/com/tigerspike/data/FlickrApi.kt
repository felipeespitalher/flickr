package com.tigerspike.data

import com.tigerspike.BuildConfig
import com.tigerspike.data.output.PhotoWrapperOutput
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("services/rest/?method=flickr.photos.fetchRecentPhotos&nojsoncallback=1&format=json")
    fun fetchRecentPhotos(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Single<PhotoWrapperOutput>

    @GET("services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json")
    fun find(
        @Query("text") text: String? = null,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Single<PhotoWrapperOutput>

}