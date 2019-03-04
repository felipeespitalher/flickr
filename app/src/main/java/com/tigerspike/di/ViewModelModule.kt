package com.tigerspike.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tigerspike.ui.commons.ViewModelFactory
import com.tigerspike.ui.commons.ViewModelKey
import com.tigerspike.ui.detail.DetailViewModel
import com.tigerspike.ui.recent.RecentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RecentViewModel::class)
    internal abstract fun bindRecentViewModel(viewModel: RecentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}