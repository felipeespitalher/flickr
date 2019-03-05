package com.tigerspike

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.tigerspike.di.AppContext
import com.tigerspike.di.AppModule
import com.tigerspike.di.DaggerAppComponent
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

open class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDagger()
        setupFresco()
        setupFabric()
    }

    internal open fun setupFabric() {
        Fabric.with(this, Crashlytics())
    }

    internal open fun setupDagger() {
        AppContext.component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    internal open fun setupFresco() {
        Fresco.initialize(this)
    }

}