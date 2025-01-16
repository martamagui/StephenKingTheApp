package com.mmag.stephenking.ui.bookDetailScreen

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.useCases.GetBookDetailUseCase
import com.mmag.stephenking.ui.screens.bookDetailScreen.BookDetailScreenViewModel
import kotlinx.coroutines.flow.flowOf
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
    }

}