package com.tigerspike

import android.app.Application
import com.tigerspike.di.AppContext
import com.tigerspike.di.DaggerAppComponent

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        AppContext.component = DaggerAppComponent.builder().build()
    }

}