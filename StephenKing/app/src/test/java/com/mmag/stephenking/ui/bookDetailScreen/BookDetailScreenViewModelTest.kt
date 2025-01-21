package com.mmag.stephenking.ui.bookDetailScreen

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.useCases.GetBookDetailUseCase
import com.mmag.stephenking.ui.screens.bookDetailScreen.BookDetailScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

class BookDetailScreenViewModelTest {

    private lateinit var viewModel: BookDetailScreenViewModel
    private val getBookDetailUseCase: GetBookDetailUseCase = mock()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = BookDetailScreenViewModel(getBookDetailUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `getBookDetail calls useCase`() = runTest {
        val bookId = 1
        val response: StephenKingResponse<Book> = StephenKingResponse.Loading()
        `when`(getBookDetailUseCase.invoke(bookId.toString())).thenReturn(flowOf(response))

        viewModel.getBookDetail(bookId.toString())
        advanceUntilIdle()

        verify(getBookDetailUseCase).invoke(bookId.toString())
    }

    @Test
    fun `getBookDetail should emit Loading and Success states when use case succeeds`() = runTest() {
        val bookId = 1
        val book: Book = mock()
        val response = StephenKingResponse.Success(book)
        `when`(getBookDetailUseCase.invoke(bookId.toString())).thenReturn(flowOf(response))

        val states = mutableListOf<StephenKingResponse<Book>>()
        val job = launch { viewModel.bookDetailScreenState.collect { states.add(it) } }

        viewModel.getBookDetail(bookId.toString())
        advanceUntilIdle()

        verify(getBookDetailUseCase).invoke(bookId.toString())
        assert(states.size == 2)
        assert( states.get(0) is StephenKingResponse.Loading)
        assert( states.get(1) == response)

        job.cancel()
    }


    @Test
    fun `getBookDetail should emit Loading and Error states when use case fails`() = runTest() {
        val id = "2"
        val response: StephenKingResponse<Book> = StephenKingResponse.Error("Error", null)
        `when`(getBookDetailUseCase.invoke(id)).thenReturn(flowOf(response))

        val states = mutableListOf<StephenKingResponse<Book>>()
        val job = launch { viewModel.bookDetailScreenState.collect { states.add(it) } }

        viewModel.getBookDetail(id)
        advanceUntilIdle()

        verify(getBookDetailUseCase).invoke(id)

        assert(states.size == 2)
        assert( states.get(0) is StephenKingResponse.Loading)
        assert( states.get(1) == response)

        job.cancel()
    }
}