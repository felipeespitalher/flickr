package com.tigerspike.exception

class MissingArgumentException(parameterKey: String) : RuntimeException() {

    override val message = "$parameterKey must not be null"

}