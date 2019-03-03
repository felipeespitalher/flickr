package com.tigerspike.ui.recent

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.tigerspike.data.output.ErrorOutput
import com.tigerspike.data.repository.PhotoRepository
import com.tigerspike.model.Photo
import com.tigerspike.reactive.Either
import com.tigerspike.reactive.compose.StepLoadingState
import com.tigerspike.reactive.compose.StepViewModel
import com.tigerspike.reactive.subscribeByEither
import com.tigerspike.ui.commons.BaseViewModel
import com.tigerspike.ui.commons.SingleEventLiveData
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class RecentViewModel @Inject constructor(
        private val photoRepository: PhotoRepository
) : BaseViewModel() {

    val loadItems = SingleEventLiveData<List<Photo>>()
    val refreshItems = SingleEventLiveData<List<Photo>>()
    val isLoading = ObservableBoolean()

    fun startUp() {
        loadPhotos()
                .subscribeByEither(
                        onError = { error ->
                            error?.let {
                                errorEvent.set(it.message)
                            }
                        },
                        onSuccess = {
                            loadItems.set(it)
                        }
                )
                .addTo(compositeDisposable)
    }

    fun onRefresh() {
        loadPhotos()
                .subscribeByEither(
                        onError = { error ->
                            error?.let {
                                errorEvent.set(it.message)
                            }
                        },
                        onSuccess = {
                            refreshItems.set(it)
                        }
                )
                .addTo(compositeDisposable)
    }

    private fun loadPhotos(): Single<Either<ErrorOutput, List<Photo>>> {
        return photoRepository
                .fetchRecentPhotos()
                .compose(StepLoadingState { isLoading ->
                    this.isLoading.set(isLoading)
                })
                .compose(StepViewModel())
    }

}