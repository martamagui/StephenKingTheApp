package com.mmag.stephenking.domain.useCases

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.repository.BookRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetBookListUseCaseTest {

    private lateinit var getBookListUseCase: GetBookListUseCase
    private val bookRepository: BookRepository = mock()

    @Before
    fun setUp() {
        getBookListUseCase = GetBookListUseCase(bookRepository)
    }

    @Test
    fun `invoke returns book list as Success`() = runTest {
        val mockBook: Book = mock()
        val mockBookList = listOf(mockBook)
        val successResponse: StephenKingResponse<List<Book>> =
            StephenKingResponse.Success(mockBookList)
        `when`(bookRepository.getBookListResponse()).thenReturn(flowOf(successResponse))

        val result = getBookListUseCase().toList()

        assertEquals(1, result.size)
        assert(result.first() is StephenKingResponse.Success)
        assertEquals(mockBookList, (result.first() as StephenKingResponse.Success<List<Book>>).data)
        verify(bookRepository).getBookListResponse()
    }

    @Test
    fun `invoke returns Loading and then Error`() = runTest {
        val errorResponse: StephenKingResponse<List<Book>> =
            StephenKingResponse.Error("Network Error")
        `when`(bookRepository.getBookListResponse()).thenReturn(flowOf(errorResponse))

        val result = getBookListUseCase().toList()

        assertEquals(1, result.size)
        assert(result.first() is StephenKingResponse.Error)
        assertEquals("Network Error", (result.first() as StephenKingResponse.Error).errorMessage)
        verify(bookRepository).getBookListResponse()
    }
}