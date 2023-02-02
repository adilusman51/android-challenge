package com.forloop.androidchallenge.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorCode: ErrorCode?
) {
    class Success<T>(data: T?, message: String? = null) : Resource<T>(data, message, null)
    class Error<T>(message: String?, errorCode: ErrorCode?, data: T? = null) :
        Resource<T>(data, message, errorCode)
}

enum class ErrorCode {
    LocationPermissionError,
    ApiError
}