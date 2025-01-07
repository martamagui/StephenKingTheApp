package com.mmag.stephenking.domain.repository

import com.mmag.stephenking.data.network.SafeApiResponse
import com.mmag.stephenking.data.network.model.BookDetailResponse
import com.mmag.stephenking.data.network.model.BookListResponse
import com.mmag.stephenking.data.network.repository.BookServiceRepository
import com.mmag.stephenking.domain.model.StephenKingResponse
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class BookRepositoryImplTest {

    private lateinit var bookRepositoryImpl: BookRepositoryImpl
    private val bookServiceRepository: BookServiceRepository = mock()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        bookRepositoryImpl = BookRepositoryImpl(bookServiceRepository)
    }

    @Test
    fun `getBookListResponse emits Loading followed by Success`() = runTest(testDispatcher) {
        val mockBookResponse: BookListResponse = mock()
        val response: SafeApiResponse<BookListResponse> = SafeApiResponse.Success(mockBookResponse)

        `when`(bookServiceRepository.getBookListResponse()).thenReturn(response)

        val flowResult = bookRepositoryImpl.getBookListResponse().toList()

        assertEquals(2, flowResult.size)
        assert(flowResult[0] is StephenKingResponse.Loading)
        assert(flowResult[1] is StephenKingResponse.Success)
    }

    @Test
    fun `getBookListResponse emits Loading followed by Error`() = runTest(testDispatcher) {
        val errorMessage = "Network Error"
        `when`(bookServiceRepository.getBookListResponse()).thenReturn(
            SafeApiResponse.Error(errorMessage)
        )

        val flowResult = bookRepositoryImpl.getBookListResponse().toList()

        assertEquals(2, flowResult.size)
        assert(flowResult[0] is StephenKingResponse.Loading)
        assert(flowResult[1] is StephenKingResponse.Error)
        assertEquals(
            errorMessage,
            (flowResult[1] as StephenKingResponse.Error).errorMessage
        )
    }

    @Test
    fun `getBookDetailResponse emits Loading followed by Success`() = runTest(testDispatcher) {
        val mockBookDetail: BookDetailResponse = mock()
        val response = SafeApiResponse.Success(mockBookDetail)

        `when`(bookServiceRepository.getBookDetailResponse("1")).thenReturn(response)

        val flowResult = bookRepositoryImpl.getBookDetailResponse("1").toList()

        assertEquals(2, flowResult.size)
        assert(flowResult[0] is StephenKingResponse.Loading)
        assert(flowResult[1] is StephenKingResponse.Success)
    }

    @Test
    fun `getBookDetailResponse emits Loading followed by Error`() = runTest(testDispatcher) {
        val errorMessage = "Book not found"
        `when`(bookServiceRepository.getBookDetailResponse("1")).thenReturn(
            SafeApiResponse.Error(errorMessage)
        )

        val flowResult = bookRepositoryImpl.getBookDetailResponse("1").toList()

        assertEquals(2, flowResult.size)
        assert(flowResult[0] is StephenKingResponse.Loading)
        assert(flowResult[1] is StephenKingResponse.Error)
        assertEquals(
            errorMessage,
            (flowResult[1] as StephenKingResponse.Error).errorMessage
        )
    }
}
