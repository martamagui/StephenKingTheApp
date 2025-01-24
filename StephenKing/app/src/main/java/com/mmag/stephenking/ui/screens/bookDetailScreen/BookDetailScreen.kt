package com.mmag.stephenking.ui.screens.bookDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
    viewModel: BookDetailScreenViewModel = hiltViewModel<BookDetailScreenViewModel>(),
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(scope) {
        viewModel.getBookDetail(bookId)
    }
    val uiState by viewModel.bookDetailScreenState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp, 42.dp)
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
        modifier = modifier.testTag("BookDetailLoadingContent"),
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
            .testTag("BookDetailErrorContent")
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
        modifier = modifier
            .testTag("BookDetailSuccessScreen")
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
    Column(modifier = modifier.testTag("BookDetailSuccessScreenSmall")) {
        Text(
            text = book.title,
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = stringResource(R.string.book_detail_year, book.year.toString()),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(R.string.book_detail_publisher, book.publisher),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(R.string.book_detail_isbn, book.iSBN),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BookDetailSuccessScreenBig(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.testTag("BookDetailSuccessScreenBig")) {
        Text(text = book.title, style = MaterialTheme.typography.displayLarge)
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.book_detail_year, book.year.toString()),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.book_detail_publisher, book.publisher),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}