package com.tigerspike.ui.recent

import android.widget.ImageView
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

    private val clickObserver: Observer<Event<Pair<Photo, ImageView>>> = mockk(relaxed = true)
    private val errorObserver: Observer<Event<Int>> = mockk(relaxed = true)

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
    private val adapter: RecentAdapter = mockk(relaxed = true)
    private val subject = RecentViewModel(photoRepository, adapter)

    @Before
    fun setup() {

        subject.errorEvent.observe(mockLifecycleOwner(), errorObserver)
    }

    @Test
    fun whenSetup_andRepositoryReturnSuccess_mustFillResponseIntoLoadItems() {
        every { photoRepository.fetchRecentPhotos() } returns Single.just(response)

        subject.setup()

        verify { photoRepository.fetchRecentPhotos() }
        verify { adapter.addAll(response) }
        verify(inverse = true) { errorObserver.onChanged(any()) }
    }

    @Test
    fun whenSetup_andRepositoryReturnError_mustSetMessageIntoErrorEvent() {
        every { photoRepository.fetchRecentPhotos() } returns Single.error(RuntimeException())

        subject.setup()

        verify { photoRepository.fetchRecentPhotos() }
        verify(inverse = true) { adapter.addAll(any()) }
        verify { errorObserver.onChanged(any()) }
    }

    @Test
    fun whenOnRefresh_andRepositoryReturnSuccess_mustFillResponseIntoLoadItems() {
        every { photoRepository.fetchRecentPhotos() } returns Single.just(response)

        subject.onRefresh()

        verify { photoRepository.fetchRecentPhotos() }
        verify { adapter.refresh(response) }
        verify(inverse = true) { errorObserver.onChanged(any()) }
    }

    @Test
    fun whenOnRefresh_andRepositoryReturnError_mustSetMessageIntoErrorEvent() {
        every { photoRepository.fetchRecentPhotos() } returns Single.error(RuntimeException())

        subject.onRefresh()

        verify { photoRepository.fetchRecentPhotos() }
        verify(inverse = true) { adapter.refresh(any()) }
        verify { errorObserver.onChanged(any()) }
    }

    @Test
    fun whenItemClick_mustInvokeItemClickEvent() {
        val photo: Photo = mockk()
        val imageView: ImageView = mockk()
        subject.itemClick.observe(mockLifecycleOwner(), clickObserver)

        subject.onItemClick(photo, imageView)

        verify { clickObserver.onChanged(Event(Pair(photo, imageView))) }
    }

}