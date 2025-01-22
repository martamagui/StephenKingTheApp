package com.mmag.stephenking.ui.bookListScreen

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.useCases.GetBookListUseCase
import com.mmag.stephenking.ui.screens.bookListScreen.BookListScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

class BookListScreenViewModelTest {

    private lateinit var viewModel: BookListScreenViewModel
    private val getBookListUseCase: GetBookListUseCase = mock()
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = BookListScreenViewModel(getBookListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init calls useCase`() = runTest {
        val bookList = listOf(mock<Book>())
        val response = StephenKingResponse.Success(bookList)
        val flow = flowOf(response)
        `when`(getBookListUseCase.invoke()).thenReturn(flow)

        viewModel.retrieveBooks()
        advanceUntilIdle()

        verify(getBookListUseCase).invoke()
    }

    @Test
    fun `init calls useCase and retrieveBooks should emit Loading and Success states when use case succeeds`() =
        runTest {
            val bookList = listOf(mock<Book>())
            val response = StephenKingResponse.Success(bookList)
            val flow = flowOf(response)
            val states = mutableListOf<StephenKingResponse<List<Book>>>()
            val job = launch { viewModel.bookListScreenSate.collect { states.add(it) } }

            `when`(getBookListUseCase.invoke()).thenReturn(flow)

            viewModel.retrieveBooks()
            advanceUntilIdle()

            verify(getBookListUseCase).invoke()
            assert(states.size == 2)
            assert(states.get(0) is StephenKingResponse.Loading)
            assert(states.get(1) == response)

            job.cancel()
        }

    @Test
    fun `init calls useCase and retrieveBooks should emit Loading and Error states when use case fails`() =
        runTest {
            val response: StephenKingResponse<List<Book>> = StephenKingResponse.Error("Error test")
            val states = mutableListOf<StephenKingResponse<List<Book>>>()
            val job = launch { viewModel.bookListScreenSate.collect { states.add(it) } }

            `when`(getBookListUseCase.invoke()).thenReturn(flowOf(response))
            viewModel.retrieveBooks()
            advanceUntilIdle()

            verify(getBookListUseCase).invoke()
            assert(states.size == 2)
            assert(states.get(0) is StephenKingResponse.Loading)
            assert(states.get(1) == response)

            job.cancel()
        }

}