package com.mmag.stephenking.data.repository

import com.mmag.stephenking.data.network.ApiService
import com.mmag.stephenking.data.network.SafeApiResponse
import com.mmag.stephenking.data.network.model.BookDetailResponse
import com.mmag.stephenking.data.network.model.BookListResponse
import com.mmag.stephenking.data.network.model.BookResponse
import com.mmag.stephenking.data.network.repository.BookServiceRepositoryRetrofit
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Response

class BookServiceRepositoryRetrofitTest {
    @Mock
    lateinit var mockApiService: ApiService

    private lateinit var bookServiceRepositoryRetrofit: BookServiceRepositoryRetrofit

    @Before
    fun setup() {
        mockApiService = mock()
        bookServiceRepositoryRetrofit = BookServiceRepositoryRetrofit(mockApiService)
    }

    @Test
    fun `test getBookListResponse success`() = runTest {
        val mockResponse = Response.success(getBookListResponse())
        `when`(mockApiService.getBookList()).thenReturn(mockResponse)

        val result = bookServiceRepositoryRetrofit.getBookListResponse()

        assert(result is SafeApiResponse.Success)
        assert((result as SafeApiResponse.Success).data != null)
    }

    @Test
    fun `test getBookListResponse failure`() = runTest {
        //(simulating a 404 Not Found)
        val mockResponse = Response.error<BookListResponse>(404, ResponseBody.create(null, ""))
        `when`(mockApiService.getBookList()).thenReturn(mockResponse)

        val result = bookServiceRepositoryRetrofit.getBookListResponse()

        assert(result is SafeApiResponse.Error)
        assert((result as SafeApiResponse.Error).errorMessage != null)
    }

    @Test
    fun `test getBookDetailResponse success`() = runTest {
        val mockResponse = Response.success(getBookDetailResponse())
        `when`(mockApiService.getBookDetail("1")).thenReturn(mockResponse)

        val result = bookServiceRepositoryRetrofit.getBookDetailResponse("1")

        assert(result is SafeApiResponse.Success)
    }

    @Test
    fun `test getBookDetailResponse failure`() = runTest {
        val mockResponse = Response.error<BookDetailResponse>(500, ResponseBody.create(null, ""))
        `when`(mockApiService.getBookDetail("1")).thenReturn(mockResponse)

        val result = bookServiceRepositoryRetrofit.getBookDetailResponse("1")

        assert(result is SafeApiResponse.Error)
        assert((result as SafeApiResponse.Error).errorMessage != null)
    }

    @Test
    fun `test getBookListResponse HttpException`() = runTest {
        val mockResponse = Response.error<BookListResponse>(403, ResponseBody.create(null, ""))
        `when`(mockApiService.getBookList()).thenReturn(mockResponse)

        val result = bookServiceRepositoryRetrofit.getBookListResponse()

        assert(result is SafeApiResponse.Error)
        assert((result as SafeApiResponse.Error).errorMessage == "Forbidden")
    }

    private fun getBookDetailResponse(): BookDetailResponse {
        val bookMock:BookResponse = mock()
        return BookDetailResponse(bookMock)
    }

    private fun getBookListResponse(): BookListResponse {
        val bookMock: BookResponse = mock()
        return BookListResponse(listOf(bookMock))
    }
}

