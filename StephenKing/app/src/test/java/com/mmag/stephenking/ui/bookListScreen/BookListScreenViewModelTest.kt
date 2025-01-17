package com.mmag.stephenking.ui.bookListScreen

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.useCases.GetBookListUseCase
import com.mmag.stephenking.ui.screens.bookListScreen.BookListScreenViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

class BookListScreenViewModelTest {

    private lateinit var viewModel: BookListScreenViewModel
    private val getBookListUseCase: GetBookListUseCase = mock()

    @Before
    fun setUp() {
        viewModel = BookListScreenViewModel(getBookListUseCase)
    }

    @Test
    fun `init calls useCase`() = runTest {
        val bookList = listOf(mock<Book>())
        val response = StephenKingResponse.Success(bookList)
        val flow = flowOf(response)
        `when`(getBookListUseCase.invoke()).thenReturn(flow)

        verify(getBookListUseCase).invoke()
    }

}