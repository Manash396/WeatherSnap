package com.mk.weathersnap.util

sealed class UiResult<out T> {
    object Loading : UiResult<Nothing>()
    data class Success<T>(val data: T) : UiResult<T>()
    data class Error(
        val message: String,
        val type : ErrorType
    ) : UiResult<Nothing>()
    object Empty : UiResult<Nothing>()
}

enum class ErrorType {
    NETWORK,
    SERVER,
    NOT_FOUND,
    UNKNOWN
}