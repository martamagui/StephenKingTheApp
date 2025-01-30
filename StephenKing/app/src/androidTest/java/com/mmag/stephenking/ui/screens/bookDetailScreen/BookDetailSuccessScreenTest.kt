package com.mmag.stephenking.ui.screens.bookDetailScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import com.mmag.stephenking.UITest
import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.Villain
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BookDetailSuccessScreenTest : UITest() {

    @get:Rule(order = 1)
    val composeRule = createComposeRule()


    @SuppressLint("UnusedBoxWithConstraintsScope")
    @Test
    fun bookDetailSuccessScreen_displaysSmallLayout_whenWidthIsSmall() {
        val book = getBookMock()

        composeRule.setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                BoxWithConstraints(modifier = Modifier.width(400.dp)) {
                    BookDetailSuccessScreen(book)
                }
            }
        }

        composeRule.onNodeWithTag("BookDetailSuccessScreenSmall").assertExists()
        composeRule.onNodeWithTag("BookDetailHeader").assertExists()
    }

    @SuppressLint("UnusedBoxWithConstraintsScope")
    @Test
    fun bookDetailSuccessScreen_displaysBigLayout_whenWidthIsLarge() {
        val book = getBookMock()

        composeRule.setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                BoxWithConstraints(modifier = Modifier.width(600.dp)) {
                    BookDetailSuccessScreen(book)
                }
            }
        }

        composeRule.onNodeWithTag("BookDetailSuccessScreenBig").assertExists()
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