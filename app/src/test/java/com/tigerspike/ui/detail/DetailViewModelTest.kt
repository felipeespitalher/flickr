package com.tigerspike.ui.detail

import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.tigerspike.MainApplicationMock
import com.tigerspike.MockkTestHelper
import com.tigerspike.model.Photo
import com.tigerspike.ui.commons.Event
import com.tigerspike.ui.provider.ResourceProvider
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = MainApplicationMock::class)
class DetailViewModelTest : MockkTestHelper() {

    private val farm = "FARM"
    private val server = "SERVER"
    private val id = "ID"
    private val secret = "SECRET"
    private val title = "TITLE"
    private val photo = Photo(
        farm,
        server,
        id,
        secret,
        title
    )

    private val resourceProvider = ResourceProvider(ApplicationProvider.getApplicationContext())
    private val subject = DetailViewModel(resourceProvider)

    @Test
    fun whenSetup_mustSetObservers() {
        subject.setup(photo)

        assertThat(subject.photo.get()).isEqualTo(photo)
        assertThat(subject.title.get()).isEqualTo(title)
        assertThat(subject.farm.get().toString()).isEqualTo("Farm :$farm")
        assertThat(subject.id.get().toString()).isEqualTo("Id :$id")
        assertThat(subject.server.get().toString()).isEqualTo("Server :$server")
        assertThat(subject.secret.get().toString()).isEqualTo("Secret :$secret")
    }

    @Test
    fun whenOnSharedPressed_mustInvokeShareObserver() {
        val shareObserver: Observer<Event<Unit>> = mockk(relaxed = true)
        subject.share.observe(mockLifecycleOwner(), shareObserver)

        subject.onSharedPressed()

        verify { shareObserver.onChanged(Event(Unit)) }
    }

}