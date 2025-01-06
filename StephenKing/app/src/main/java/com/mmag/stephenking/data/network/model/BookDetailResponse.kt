package com.mmag.stephenking.data.network.model

import com.google.gson.annotations.SerializedName

data class BookDetailResponse(
    @SerializedName("data")
    val bookList: BookResponse,
)
