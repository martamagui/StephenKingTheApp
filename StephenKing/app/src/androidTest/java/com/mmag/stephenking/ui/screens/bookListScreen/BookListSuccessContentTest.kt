package com.mmag.stephenking.ui.screens.bookListScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.mmag.stephenking.domain.model.Book
import org.junit.Rule
import org.junit.Test

class BookListSuccessContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun bookListSuccessContent_displaysAllBooks() {
        val books = getFakeList()
        composeRule.setContent {
            BookListSuccessContent(
                content = books,
                onBookClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }

        composeRule.onNodeWithTag("BookListSuccessContent").assertExists()
        composeRule.onNodeWithTag("BookListLazyColumn").assertExists()
        composeRule.onNodeWithTag("NoBooksFound").assertDoesNotExist()
        books.forEach { book ->
            composeRule.onNodeWithTag("BookListItem${book.id}").assertExists()
        }
    }

    @Test
    fun bookListSuccessContent_displaysEmptyBooks() {
        composeRule.setContent {
            BookListSuccessContent(
                content = emptyList(),
                onBookClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }

        composeRule.onNodeWithTag("BookListSuccessContent").assertExists()
        composeRule.onNodeWithTag("NoBooksFound").assertExists()
        composeRule.onNodeWithTag("BookListLazyColumn").assertDoesNotExist()
        composeRule.onNodeWithTag("NoBooksFound")
            .assert(hasText("It seems like there is no books yet."))
    }

    private fun getFakeList() = listOf(
        Book(
            "Title1",
            "Description",
            "Image",
            1,
            listOf(),
            561,
            "Publisher1",
            "Title",
            listOf(),
            2021
        ), Book(
            "Title2",
            "Description",
            "Image",
            2,
            listOf(),
            561,
            "Publisher2",
            "Title",
            listOf(),
            2021
        ), Book(
            "Title3",
            "Description3",
            "Image",
            3,
            listOf(),
            561,
            "Publisher",
            "Title",
            listOf(),
            2021
        )
    )

}