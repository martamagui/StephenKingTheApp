package com.mmag.stephenking.ui.screens.bookDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmag.stephenking.R
import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.ui.commonComponents.StephenKingCircularProgressIndicator
import com.mmag.stephenking.ui.commonComponents.cards.ErrorCard
import com.mmag.stephenking.ui.screens.bookListScreen.BookListErrorContent

@Composable
fun BookDetailScreen(
    bookId: String,
    viewModel: BookDetailScreenViewModel = hiltViewModel<BookDetailScreenViewModel>().apply {
        getBookDetail(bookId)
    },
) {

    val uiState by viewModel.bookDetailScreenState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .testTag("BookDetailScreen: $bookId")
    ) {
        when (uiState) {
            is StephenKingResponse.Loading -> BookDetailLoadingContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            )

            is StephenKingResponse.Success -> {
                if (uiState.data == null) {
                    BookDetailErrorContent(
                        modifier = Modifier.fillMaxSize(),
                        errorMessage = stringResource(R.string.default_no_data_error_message)
                    )
                    return@Box
                } else {
                    BookDetailSuccessScreen(uiState.data!!, Modifier.fillMaxSize())
                }
            }

            is StephenKingResponse.Error -> BookListErrorContent(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun BookDetailLoadingContent(modifier: Modifier) {
    Box(
        modifier = modifier.testTag("BookListLoadingContent"),
        contentAlignment = Alignment.Center
    ) {
        StephenKingCircularProgressIndicator(Modifier.testTag("BookInProgress"))
    }
}


@Composable
fun BookDetailErrorContent(
    modifier: Modifier,
    errorMessage: String = stringResource(R.string.default_error_message),
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .padding(12.dp)
            .testTag("BookListErrorContent")
    ) {
        ErrorCard(errorMessage, Modifier.fillMaxWidth())
    }
}

@Composable
fun BookDetailSuccessScreen(
    book: Book,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .testTag("BookDetailSuccessScreen: ${book.id}")
    ) {
        if (maxWidth < 400.dp) {
            BookDetailSuccessScreenSmall(book, Modifier.matchParentSize())
        } else {
            BookDetailSuccessScreenBig(book, Modifier.matchParentSize())
        }
    }

}

@Composable
fun BookDetailSuccessScreenSmall(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) { }
}

@Composable
fun BookDetailSuccessScreenBig(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) { }
}