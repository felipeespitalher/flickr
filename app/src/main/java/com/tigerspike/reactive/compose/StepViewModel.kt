package com.tigerspike.reactive.compose

import com.tigerspike.data.output.ErrorOutput
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class StepViewModel<T> :
    SingleTransformer<T, Either<ErrorOutput, T>> {

    override fun apply(upstream: Single<T>): SingleSource<Either<ErrorOutput, T>> {
        return upstream
            .compose(TransformerEitherApi())
            .compose(StepIoToMain())
            .compose(StepLogError())
    }
}
