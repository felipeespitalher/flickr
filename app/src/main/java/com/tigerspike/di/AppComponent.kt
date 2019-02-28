package com.tigerspike.di

import com.tigerspike.ui.recent.RecentActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(activity: RecentActivity)

}