package com.tigerspike.reactive

import com.tigerspike.data.output.ErrorOutput
import com.tigerspike.extension.getApiError

class TransformerEitherApi<V> : TransformerEither<ErrorOutput, V>(
    mapError = {
        it.getApiError()
    }
)
