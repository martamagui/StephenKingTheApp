package com.mmag.stephenking.data.network.model


import com.google.gson.annotations.SerializedName

data class BookListResponse(
    @SerializedName("data")
    val bookList: List<BookResponse>
)