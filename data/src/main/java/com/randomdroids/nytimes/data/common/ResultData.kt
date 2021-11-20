package com.randomdroids.nytimes.data.common

sealed class ResultData<out T> {

    data class Success<out T>(val value: T) : ResultData<T>()
    data class Failure<out T>(val throwable : Throwable) : ResultData<T>()

    companion object {
        fun <T> success( value: T ): ResultData<T> = Success(value)
        fun <T> failure( throwable: Throwable ): ResultData<T> = Failure(throwable)
    }

}