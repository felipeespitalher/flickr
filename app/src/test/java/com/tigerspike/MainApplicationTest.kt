package com.tigerspike

import com.facebook.drawee.backends.pipeline.Fresco
import com.tigerspike.di.AppContext
import com.tigerspike.di.DaggerAppComponent
import io.fabric.sdk.android.Fabric
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MainApplicationTest {

    private val subject = MainApplication()


    @Test
    fun whenOnCreate_mustInitializeDagger_andInitializeFresco() {
        mockkStatic(Fresco::class)
        mockkStatic(DaggerAppComponent::class)
        mockkStatic(Fabric::class)
        every { Fabric.with(any(), any()) } answers { nothing }
        every { Fresco.initialize(any()) } answers { nothing }

        subject.onCreate()

        assertThat(AppContext.component).isNotNull
        verify { DaggerAppComponent.builder() }
        verify { Fresco.initialize(any()) }
        verify { Fabric.with(any(), any()) }
    }

}