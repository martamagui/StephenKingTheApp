package com.mmag.stephenking.data.network

sealed class SafeApiResponse<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T?) : SafeApiResponse<T>(data = data)
    class Error<T>(message: String?, data: T? = null) : SafeApiResponse<T>(data = data, message)
    class Loading<T>: SafeApiResponse<T>()
}