package com.tigerspike

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.mockk.every
import io.mockk.mockk

abstract class MockkTestHelper {
    fun mockLifecycleOwner(): LifecycleOwner {
        val lifecycleOwner = mockk<LifecycleOwner>()

        val registry = LifecycleRegistry(lifecycleOwner)
        registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        every { lifecycleOwner.lifecycle } returns registry

        return lifecycleOwner
    }
}