package com.mmag.stephenking.domain.model

sealed class StephenKingResponse<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T?) : StephenKingResponse<T>(data = data)
    class Error<T>(message: String?, data: T? = null) : StephenKingResponse<T>(data = data, message)
    class Loading<T>: StephenKingResponse<T>()
}