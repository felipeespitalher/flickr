package com.tigerspike

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.tigerspike.di.AppContext
import com.tigerspike.di.DaggerAppComponent

open class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDagger()
        setupFresco()
    }

    internal open fun setupDagger() {
        AppContext.component = DaggerAppComponent.builder().build()
    }

    internal open fun setupFresco() {
        Fresco.initialize(this)
    }

}