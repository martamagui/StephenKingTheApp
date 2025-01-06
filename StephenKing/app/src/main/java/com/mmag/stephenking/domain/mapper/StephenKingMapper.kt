package com.mmag.stephenking.domain.mapper

import com.mmag.stephenking.data.network.SafeApiResponse
import com.mmag.stephenking.domain.model.StephenKingResponse

fun <T> SafeApiResponse<T>.toStephenKingResponse(): StephenKingResponse<T> {
    return when (this) {
        is SafeApiResponse.Success -> StephenKingResponse.Success(data)
        is SafeApiResponse.Error -> StephenKingResponse.Error(errorMessage, data)
    }
}