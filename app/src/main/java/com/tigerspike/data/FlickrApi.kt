package com.tigerspike.data

import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("/")
    fun fetch(
        @Query("id") id: String? = null,
        @Query("ids") ids: List<String>? = null,
        @Query("tags") tags: List<String>? = null
    )

}