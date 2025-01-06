package com.mmag.stephenking.data.network.repository

import com.mmag.stephenking.data.network.SafeApiResponse
import com.mmag.stephenking.data.network.model.BookDetailResponse
import com.mmag.stephenking.data.network.model.BookListResponse

interface BookServiceRepository {
    suspend fun getBookListResponse(): SafeApiResponse<BookListResponse>
    suspend fun getBookDetailResponse(bookId:String): SafeApiResponse<BookDetailResponse>
}