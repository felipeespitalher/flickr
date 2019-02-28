package com.tigerspike.reactive.compose

import io.reactivex.*

class StepLoadingState<T>(val updateLoadingState: (Boolean) -> Unit) :
        SingleTransformer<T, T>,
        ObservableTransformer<T, T> {

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

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
                .doOnSubscribe {
                    updateLoadingState(true)
                }
                .doOnError {
                    updateLoadingState(false)
                }
                .doOnComplete {
                    updateLoadingState(false)
                }
    }
}
