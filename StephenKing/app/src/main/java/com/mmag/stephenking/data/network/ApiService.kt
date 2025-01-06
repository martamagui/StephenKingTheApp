package com.mmag.stephenking.data.network

import com.mmag.stephenking.BuildConfig
import com.mmag.stephenking.data.network.model.BookDetailResponse
import com.mmag.stephenking.data.network.model.BookListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(BuildConfig.BOOKS)
    suspend fun getBookList(): Response<BookListResponse>

    @GET(BuildConfig.BOOK_DETAIL)
    suspend fun getBookDetail(@Path("bookId") bookId: String): Response<BookDetailResponse>

}