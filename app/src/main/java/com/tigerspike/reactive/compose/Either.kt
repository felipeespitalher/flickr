package com.tigerspike.reactive.compose

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy


sealed class Either<out E, out V> {

    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()

    companion object {
        fun <V> value(value: V): Either<Nothing, V> = Value(value)
        fun <E> error(value: E): Either<E, Nothing> = Error(value)
    }

}

fun <E, V, R> Either<E, V>.either(error: (E) -> R, value: (V) -> R) = when (this) {
    is Either.Error -> error(this.error)
    is Either.Value -> value(this.value)
}

fun <E, V> Either<E, V>.error(): E? = when (this) {
    is Either.Error -> this.error
    is Either.Value -> null
}

fun <E, V> Either<E, V>.value(): V? = when (this) {
    is Either.Error -> null
    is Either.Value -> this.value
}

fun <E, V, M> Either<E, V>.mapValue(valueMapper: (V) -> M): Either<E, M> = when (this) {
    is Either.Error -> this
    is Either.Value -> Either.value(valueMapper(this.value))
}

fun <E, V, M> Either<E, V>.mapError(errorMapper: (E) -> M): Either<M, V> = when (this) {
    is Either.Error -> Either.error(errorMapper(this.error))
    is Either.Value -> this
}

fun <E, V, M> Single<Either<E, V>>.mapEitherValue(valueMapper: (V) -> M): Single<Either<E, M>> =
        this.map { either ->
            when (either) {
                is Either.Error -> either
                is Either.Value -> either.mapValue(valueMapper)
            }
        }

fun <E, V, M> Single<Either<E, V>>.mapEitherError(errorMapper: (E) -> M): Single<Either<M, V>> =
        this.map { either ->
            when (either) {
                is Either.Error -> either.mapError(errorMapper)
                is Either.Value -> either
            }
        }

@CheckReturnValue
fun <A : Either<E, D>, E, D> Single<A>.subscribeByEither(
        onError: (E?) -> Unit,
        onSuccess: (D) -> Unit
): Disposable {

    return subscribeBy(
            onError = {
                onError(null)
            },
            onSuccess = { eitherResource ->
                eitherResource.either(
                        error = {
                            onError(it)
                        },
                        value = {
                            onSuccess(it)
                        }
                )
            }
    )
}

@CheckReturnValue
fun <A : Either<E, D>, E, D> Observable<A>.subscribeByEither(
        onError: (E?) -> Unit,
        onNext: (D) -> Unit,
        onComplete: () -> Unit
): Disposable {

    return subscribeBy(
            onError = {
                onError(null)
            },
            onNext = { eitherResource ->
                eitherResource.either(
                        error = {
                            onError(it)
                        },
                        value = {
                            onNext(it)
                        }
                )
            },
            onComplete = {
                onComplete()
            }
    )
}