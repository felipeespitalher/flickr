package com.tigerspike.reactive

import com.tigerspike.data.output.ErrorOutput
import com.tigerspike.data.output.getApiError

class TransformerEitherApi<V> : TransformerEither<ErrorOutput, V>(
    mapError = {
        it.getApiError()
    }
)
