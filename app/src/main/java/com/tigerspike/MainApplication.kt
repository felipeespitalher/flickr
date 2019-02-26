package com.tigerspike

import android.app.Application
import com.tigerspike.di.mainModule
import com.tigerspike.di.networkModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, mainModule + networkModule)
    }

}