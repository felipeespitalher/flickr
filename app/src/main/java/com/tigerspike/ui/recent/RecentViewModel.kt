package com.tigerspike.ui.recent

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.tigerspike.data.repository.PhotoRepository
import com.tigerspike.reactive.compose.StepLoadingState
import com.tigerspike.reactive.compose.StepViewModel
import com.tigerspike.reactive.compose.subscribeByEither
import com.tigerspike.ui.commons.BaseViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class RecentViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : BaseViewModel() {

    val loading = ObservableBoolean()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun loadPhotos() {
        photoRepository
            .fetchRecentPhotos()
            .compose(StepLoadingState { isLoading ->
                loading.set(isLoading)
            })
            .compose(StepViewModel())
            .subscribeByEither(
                onError = {

                },
                onSuccess = {

                }
            )
            .addTo(compositeDisposable)

    }

}