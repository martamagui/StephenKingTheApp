package com.mmag.stephenking.ui.screens.bookDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BookDetailScreen(
    bookId: String,
    viewModel: BookDetailScreenViewModel = hiltViewModel<BookDetailScreenViewModel>().apply {
        getBookDetail(bookId)
    }
) {

    val uiState by viewModel.bookDetailScreenState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .testTag("BookDetailScreen: $bookId")
    ) { }
}