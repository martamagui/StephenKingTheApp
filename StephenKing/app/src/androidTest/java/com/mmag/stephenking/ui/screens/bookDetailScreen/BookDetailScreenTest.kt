package com.mmag.stephenking.ui.screens.bookDetailScreen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.mmag.stephenking.UITest
import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.repository.FakeBookRepository
import com.mmag.stephenking.domain.useCases.GetBookDetailUseCase
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BookDetailScreenTest : UITest() {

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    private lateinit var fakeViewModel: BookDetailScreenViewModel
    private var fakeBookRepository = FakeBookRepository()
    private val fakeBookId = 1


    override fun setUp() {
        super.setUp()

        fakeViewModel = BookDetailScreenViewModel(GetBookDetailUseCase(fakeBookRepository))
        fakeBookRepository.fakeDetailState.value = StephenKingResponse.Loading()
        composeRule.setContent {
            BookDetailScreen(fakeBookId.toString(), fakeViewModel, {1==1})
        }
    }

    @Test
    fun bookListScreen_showsContent() {
        fakeBookRepository.fakeDetailState.value = StephenKingResponse.Error("Error")

        composeRule.onNodeWithTag("BookDetailScreen: $fakeBookId").assertExists()
    }

    @Test
    fun bookListScreen_showsErrorContent_onErrorStateWithCustomMessage() {
        fakeBookRepository.fakeDetailState.value = StephenKingResponse.Error("Error")

        composeRule.onNodeWithTag("BookDetailErrorContent").assertExists()
        composeRule.onNodeWithText("Error").assertExists()
    }

    @Test
    fun bookListScreen_showsErrorContent_onErrorStateWithNoErrorMessage() {
        fakeBookRepository.fakeDetailState.value = StephenKingResponse.Error(null)

        composeRule.onNodeWithTag("BookDetailErrorContent").assertExists()
        composeRule.onNodeWithText("Looks like something went wrong :(").assertExists()
    }

    @Test
    fun bookDetailScreen_showsLoadingContent_onLoadingState() {
        fakeBookRepository.fakeDetailState.value = StephenKingResponse.Loading()

        composeRule.onNodeWithTag("BookDetailLoadingContent").assertExists()
    }

    @Test
    fun bookDetailScreen_showsSuccessContent_onSuccessState() {
        fakeBookRepository.fakeDetailState.value = StephenKingResponse.Success(getBookMock())

        composeRule.onNodeWithTag("BookDetailSuccessScreen").assertExists()
    }

    @Test
    fun bookDetailScreen_showsErrorContent_onSuccessButEmptyDataState() {
        fakeBookRepository.fakeDetailState.value = StephenKingResponse.Success(null)

        composeRule.onNodeWithTag("BookDetailErrorContent").assertExists()
        composeRule.onNodeWithText("We couldn't find what you are looking for").assertExists()
    }

    private fun getBookMock() = Book(
        createdAt = "2025-01-01",
        handle = "mock-handle",
        iSBN = "123-4567890123",
        id = 1,
        notes = listOf("Mock note 1", "Mock note 2"),
        pages = 300,
        publisher = "Mock Publisher",
        title = "Mock Title",
        villains = listOf(),
        year = 2025
    )
}