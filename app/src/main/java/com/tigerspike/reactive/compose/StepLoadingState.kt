package com.tigerspike.reactive.compose

import io.reactivex.*

class StepLoadingState<T>(val updateLoadingState: (Boolean) -> Unit) :
    SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
            .doOnSubscribe {
                updateLoadingState(true)
            }
            .doOnError {
                updateLoadingState(false)
            }
            .doOnSuccess {
                updateLoadingState(false)
            }
    }
}
