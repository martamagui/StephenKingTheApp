package com.mmag.stephenking.ui.bookDetailScreen

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.useCases.GetBookDetailUseCase
import com.mmag.stephenking.ui.screens.bookDetailScreen.BookDetailScreenViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

class BookDetailScreenViewModelTest {

    private lateinit var viewModel: BookDetailScreenViewModel
    private val getBookDetailUseCase: GetBookDetailUseCase = mock()

    @Before
    fun setUp() {
        viewModel = BookDetailScreenViewModel(getBookDetailUseCase)
    }

    @Test
    fun `getBookDetail calls useCase`() = runTest {
        val bookId = 1
        val book: Book = mock()
        `when`(book.id).thenReturn(bookId)
        val response = StephenKingResponse.Success(book)
        val flow = flowOf(response)
        `when`(getBookDetailUseCase.invoke(bookId.toString())).thenReturn(flow)

        viewModel.getBookDetail(bookId.toString())

        verify(getBookDetailUseCase).invoke(bookId.toString())
        runCurrent()
        assertEquals(response, viewModel.bookDetailScreenState.value)
    }

    @Test
    fun `getBookDetail should emit Loading and Success states when use case succeeds`() = runTest {
        val bookId = 1
        val book: Book = mock()
        `when`(book.id).thenReturn(bookId)
        val response = StephenKingResponse.Success(book)
        val flow = flowOf(response)
        `when`(getBookDetailUseCase.invoke(bookId.toString())).thenReturn(flow)

        assertEquals(viewModel.bookDetailScreenState.value is StephenKingResponse.Loading, true)

        viewModel.getBookDetail(bookId.toString())

        verify(getBookDetailUseCase).invoke(bookId.toString())
        runCurrent()
        assertEquals(response, viewModel.bookDetailScreenState.value)
    }


    @Test
    fun `getBookDetail should emit Loading and Error states when use case fails`() = runTest {
        val id = "2"
        val response: StephenKingResponse<Book> = StephenKingResponse.Error("Error", null)
        val flow = flowOf(response)
        `when`(getBookDetailUseCase.invoke(id)).thenReturn(flow)

        assertEquals(viewModel.bookDetailScreenState.value is StephenKingResponse.Loading, true)

        viewModel.getBookDetail(id)

        verify(getBookDetailUseCase).invoke(id)
        runCurrent()
        assertEquals(response, viewModel.bookDetailScreenState.value)
    }
}