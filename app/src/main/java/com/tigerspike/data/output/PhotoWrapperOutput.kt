package com.tigerspike.data.output

data class PhotoWrapperOutput(
        val page: Int,
        val pages: Int,
        val total: Int,
        val photo: List<PhotoOutput>
)