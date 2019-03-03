package com.tigerspike

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.tigerspike.di.AppContext
import com.tigerspike.di.DaggerAppComponent

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDagger()
        setupFresco()
    }

    private fun setupDagger() {
        AppContext.component = DaggerAppComponent.builder().build()
    }

    private fun setupFresco(){
        Fresco.initialize(this)
    }

}