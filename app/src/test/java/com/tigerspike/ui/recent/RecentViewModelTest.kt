package com.tigerspike.ui.recent

import androidx.lifecycle.Observer
import com.tigerspike.MainApplicationMock
import com.tigerspike.MockkTestHelper
import com.tigerspike.SynchronousTestSchedulerRule
import com.tigerspike.data.repository.PhotoRepository
import com.tigerspike.model.Photo
import com.tigerspike.ui.commons.Event
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = MainApplicationMock::class)
class RecentViewModelTest : MockkTestHelper() {

    @get:Rule
    val sync = SynchronousTestSchedulerRule()

    private val loadObserver: Observer<Event<List<Photo>>> = mockk(relaxed = true)
    private val refreshObserver: Observer<Event<List<Photo>>> = mockk(relaxed = true)
    private val errorObserver: Observer<Event<String>> = mockk(relaxed = true)

    private val farm = "FARM"
    private val server = "SERVER"
    private val id = "ID"
    private val secret = "SECRET"
    private val title = "TITLE"

    private val response = listOf(
        Photo(
            farm,
            server,
            id,
            secret,
            title
        )
    )

    private val photoRepository: PhotoRepository = mockk()
    private val subject = RecentViewModel(photoRepository)

    @Before
    fun setup() {
        subject.loadItems.observe(mockLifecycleOwner(), loadObserver)
        subject.refreshItems.observe(mockLifecycleOwner(), refreshObserver)
        subject.errorEvent.observe(mockLifecycleOwner(), errorObserver)
    }

    @Test
    fun whenStartUp_andRepositoryReturnSuccess_mustFillResponseIntoLoadItems() {
        every { photoRepository.fetchRecentPhotos() } returns Single.just(response)

        subject.startUp()

        verify { photoRepository.fetchRecentPhotos() }
        verify { loadObserver.onChanged(Event(response)) }
        verify(inverse = true) { errorObserver.onChanged(any()) }
    }

    @Test
    fun whenStartUp_andRepositoryReturnError_mustSetMessageIntoErrorEvent() {
        every { photoRepository.fetchRecentPhotos() } returns Single.error(RuntimeException())

        subject.startUp()

        verify { photoRepository.fetchRecentPhotos() }
        verify(inverse = true) { loadObserver.onChanged(any()) }
        verify { errorObserver.onChanged(any()) }
    }

    @Test
    fun whenOnRefresh_andRepositoryReturnSuccess_mustFillResponseIntoLoadItems() {
        every { photoRepository.fetchRecentPhotos() } returns Single.just(response)

        subject.onRefresh()

        verify { photoRepository.fetchRecentPhotos() }
        verify { refreshObserver.onChanged(Event(response)) }
        verify(inverse = true) { errorObserver.onChanged(any()) }
    }

    @Test
    fun whenOnRefresh_andRepositoryReturnError_mustSetMessageIntoErrorEvent() {
        every { photoRepository.fetchRecentPhotos() } returns Single.error(RuntimeException())

        subject.onRefresh()

        verify { photoRepository.fetchRecentPhotos() }
        verify(inverse = true) { refreshObserver.onChanged(any()) }
        verify { errorObserver.onChanged(any()) }
    }

}