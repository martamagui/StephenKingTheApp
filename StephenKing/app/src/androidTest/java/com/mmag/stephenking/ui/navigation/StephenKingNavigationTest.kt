package com.mmag.stephenking.ui.navigation


import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.mmag.stephenking.UITest
import com.mmag.stephenking.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class StephenKingNavigationTest: UITest() {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testStartDestination_IsBookList() {
        composeTestRule.onNodeWithTag("StephenKingNavigation").assertExists()
        composeTestRule.onNodeWithTag("BookListScreen").assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testNavigateToBookDetailScreen_IsBookDetailScreenDisplayed() {
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag("BookListItem1"))
        composeTestRule.onNodeWithTag("BookListItem1").performClick()
        composeTestRule.onNodeWithTag("BookDetailScreen: 1").assertExists()
    }

}