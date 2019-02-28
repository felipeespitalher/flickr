package com.tigerspike.reactive.compose

import io.reactivex.*

open class TransformerEither<E, V>  (
        val mapError: (Throwable) -> E
) :
        SingleTransformer<V, Either<E, V>>,
        ObservableTransformer<V, Either<E, V>> {

    override fun apply(upstream: Single<V>): SingleSource<Either<E, V>> {
        return upstream
                .map(this::mapDataToResource)
                .onErrorReturn(this::mapThrowableToResource)
    }

    override fun apply(upstream: Observable<V>): ObservableSource<Either<E, V>> {
        return upstream
                .map(this::mapDataToResource)
                .onErrorReturn(this::mapThrowableToResource)
    }

    private fun mapDataToResource(value: V): Either<E, V> {
        return Either.value(value)
    }

    private fun mapThrowableToResource(throwable: Throwable): Either<E, V> {
        return Either.error(mapError(throwable))
    }
}
