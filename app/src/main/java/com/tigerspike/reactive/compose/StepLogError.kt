package com.tigerspike.reactive.compose

import com.tigerspike.reactive.Either
import com.tigerspike.reactive.error
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import timber.log.Timber

class StepLogError<E, V> :
    SingleTransformer<Either<E, V>, Either<E, V>> {

    override fun apply(upstream: Single<Either<E, V>>): SingleSource<Either<E, V>> {
        return upstream
            .flatMap(this::logEitherErrorIfNecessary)
            .doOnError { Timber.e(it) }
    }

    private fun logEitherErrorIfNecessary(value: Either<E, V>): SingleSource<Either<E, V>> {
        return Single.fromCallable {
            value.error()?.let { Timber.i(it.toString()) }
            return@fromCallable value
        }
    }

}
