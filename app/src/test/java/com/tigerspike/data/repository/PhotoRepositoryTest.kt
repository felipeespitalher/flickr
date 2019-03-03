package com.tigerspike.data.repository

import com.tigerspike.data.FlickrApi
import com.tigerspike.data.output.PhotoContainerOutput
import com.tigerspike.data.output.PhotoOutput
import com.tigerspike.data.output.PhotoWrapperOutput
import com.tigerspike.model.Photo
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test


class PhotoRepositoryTest {

    private val farm = "FARM"
    private val server = "SERVER"
    private val id = "ID"
    private val secret = "SECRET"
    private val title = "TITLE"

    private val photo = PhotoOutput(
        farm,
        server,
        id,
        secret,
        title
    )

    private val wrapperPhoto = PhotoWrapperOutput(
        1,
        100,
        1000,
        listOf(photo)
    )

    private val response = Photo(
        farm,
        server,
        id,
        secret,
        title
    )

    private val containerOutput = PhotoContainerOutput(wrapperPhoto)

    private val flickrApi: FlickrApi = mockk(relaxed = true)
    private val subject = PhotoRepository(flickrApi)

    @Test
    fun whenFetchRecentPhotos_mustInvokeFlickrApi_andConvertResultToADomainObject() {
        every { flickrApi.fetchRecentPhotos() } returns Single.just(containerOutput)

        subject
            .fetchRecentPhotos()
            .test()
            .assertComplete()
            .assertOf {
                listOf(response)
            }

        verify { flickrApi.fetchRecentPhotos() }
    }
}