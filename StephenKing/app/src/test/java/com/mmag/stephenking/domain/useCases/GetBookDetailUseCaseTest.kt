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

class GetBookDetailUseCaseTest {
    private lateinit var getBookDetailUseCase: GetBookDetailUseCase
    private val bookRepository: BookRepository = mock()

    @Before
    fun setUp() {
        getBookDetailUseCase = GetBookDetailUseCase(bookRepository)
    }

    @Test
    fun `invoke returns book detail as Success`() = runTest {
        val bookId = 1
        val mockBook: Book = mock()
        `when`(mockBook.id).thenReturn(bookId)
        val successResponse = StephenKingResponse.Success(mockBook)
        `when`(bookRepository.getBookDetailResponse(bookId.toString())).thenReturn(flowOf(successResponse))

        val result = getBookDetailUseCase(bookId.toString()).toList()

        assertEquals(1, result.size)
        assert(result.first() is StephenKingResponse.Success)
        assertEquals(mockBook, (result.first() as StephenKingResponse.Success<Book>).data)
        verify(bookRepository).getBookDetailResponse(bookId.toString())
    }

    @Test
    fun `invoke returns Loading and then Error`() = runTest {
        val bookId = "1"
        val errorResponse: StephenKingResponse<Book> = StephenKingResponse.Error("Book not found", null)
        `when`(bookRepository.getBookDetailResponse(bookId)).thenReturn(flowOf(errorResponse))

        val result = getBookDetailUseCase(bookId).toList()

        assertEquals(1, result.size)
        assert(result.first() is StephenKingResponse.Error)
        assertEquals("Book not found", (result.first() as StephenKingResponse.Error).errorMessage)
        verify(bookRepository).getBookDetailResponse(bookId)
    }
}