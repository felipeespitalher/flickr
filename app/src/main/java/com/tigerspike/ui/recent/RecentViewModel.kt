package com.tigerspike.ui.recent

import android.widget.ImageView
import androidx.databinding.ObservableBoolean
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
        private val photoRepository: PhotoRepository,
        val adapter: RecentAdapter
) : BaseViewModel(), RecentAdapter.Listener {

    val itemClick = SingleEventLiveData<Pair<Photo, ImageView>>()
    val isLoading = ObservableBoolean()

    init {
        adapter.listener = this
    }

    fun startUp() {
        loadPhotos()
                .subscribeByEither(
                        onError = { error ->
                            error?.let {
                                errorEvent.set(it.message)
                            }
                        },
                        onSuccess = {
                            adapter.addAll(it)
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
                            adapter.refresh(it)
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

    override fun onItemClick(photo: Photo, imageView: ImageView) {
        itemClick.set(Pair(photo, imageView))
    }

}