package com.mmag.stephenking.data.network.repository

import com.mmag.stephenking.data.network.ApiService
import com.mmag.stephenking.data.network.SafeApiResponse
import com.mmag.stephenking.data.network.SafeService
import com.mmag.stephenking.data.network.model.BookDetailResponse
import com.mmag.stephenking.data.network.model.BookListResponse
import javax.inject.Inject

class BookServiceRepositoryRetrofit @Inject constructor(
    private val service: ApiService,
) : BookServiceRepository, SafeService() {

    override suspend fun getBookListResponse(): SafeApiResponse<BookListResponse> =
        safeApiCall { service.getBookList() }

    override suspend fun getBookDetailResponse(bookId: String): SafeApiResponse<BookDetailResponse> =
        safeApiCall { service.getBookDetail(bookId) }
}