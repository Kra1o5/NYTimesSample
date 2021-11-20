package com.randomdroids.nytimes.data.common

class Response<T>(
    private val value: T? = null,
    private val error: Throwable? = null
) {
    fun getValue(): T? {
        error?.let { throwable -> throw throwable }
        return value
    }
}