package com.mmag.stephenking.ui.screens.bookListScreen


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.mmag.stephenking.UITest
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.repository.FakeBookRepository
import com.mmag.stephenking.domain.useCases.GetBookListUseCase
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BookListScreenTest : UITest() {

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    private lateinit var fakeViewModel: BookListScreenViewModel
    private var fakeBookRepository = FakeBookRepository()

    override fun setUp() {
        super.setUp()

        fakeViewModel = BookListScreenViewModel(GetBookListUseCase(fakeBookRepository))
        fakeBookRepository.fakeListState.value = StephenKingResponse.Loading()
        composeRule.setContent {
            BookListScreen(viewModel = fakeViewModel, onBookClick = {})
        }
    }


    @Test
    fun bookListScreen_showsErrorContent_onErrorState() {
        fakeBookRepository.fakeListState.value = StephenKingResponse.Error("Error")

        composeRule.onNodeWithTag("BookListErrorContent").assertExists()
    }

    @Test
    fun bookListScreen_showsLoadingContent_onLoadingState() {
        fakeBookRepository.fakeListState.value = StephenKingResponse.Loading()

        composeRule.onNodeWithTag("BookListLoadingContent").assertExists()
    }

    @Test
    fun bookListScreen_showsSuccessContent_onSuccessState() {
        fakeBookRepository.fakeListState.value = StephenKingResponse.Success(emptyList())

        composeRule.onNodeWithTag("BookListSuccessContent").assertExists()
    }
}

