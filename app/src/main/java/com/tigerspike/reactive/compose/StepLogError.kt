package com.tigerspike.reactive.compose

import io.reactivex.*
import timber.log.Timber

class StepLogError<E, V> :
    SingleTransformer<Either<E, V>, Either<E, V>>,
    ObservableTransformer<Either<E, V>, Either<E, V>> {

    override fun apply(upstream: Single<Either<E, V>>): SingleSource<Either<E, V>> {
        return upstream
            .flatMap(this::logEitherErrorIfNecessary)
            .doOnError { logError(it) }
    }

    override fun apply(upstream: Observable<Either<E, V>>): ObservableSource<Either<E, V>> {
        return upstream
            .flatMapSingle(this::logEitherErrorIfNecessary)
            .doOnError { logError(it) }
    }

    private fun logEitherErrorIfNecessary(value: Either<E, V>): SingleSource<Either<E, V>> {
        return Single.fromCallable {
            value.error()?.let { Timber.i(it.toString()) }
            return@fromCallable value
        }
    }

    private fun logError(throwable: Throwable) {
        Timber.e(throwable)
    }
}
