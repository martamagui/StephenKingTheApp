package com.mmag.stephenking.ui.screens.bookListScreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmag.stephenking.R
import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.ui.commonComponents.BookListListScreenTopAppBar
import com.mmag.stephenking.ui.commonComponents.StephenKingCircularProgressIndicator
import com.mmag.stephenking.ui.commonComponents.cards.ErrorCard

@Composable
fun BookListScreen(
    viewModel: BookListScreenViewModel = hiltViewModel<BookListScreenViewModel>().apply {
        retrieveBooks()
    },
    onBookClick: (String) -> Unit,
) {
    val uiState by viewModel.bookListScreenSate.collectAsState()

    Scaffold(
        topBar = { BookListListScreenTopAppBar() },
        modifier = Modifier.testTag("BookListScreen")
    ) { paddingValues ->
        when (uiState) {
            is StephenKingResponse.Error -> BookListErrorContent(
                modifier = Modifier.fillMaxSize()
            )

            is StephenKingResponse.Loading -> BookListLoadingContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )

            is StephenKingResponse.Success -> BookListSuccessContent(
                content = uiState.data ?: emptyList(),
                onBookClick = { id -> onBookClick(id) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
        }
    }
}

@Composable
fun BookListLoadingContent(modifier: Modifier) {
    Box(
        modifier = modifier.testTag("BookListLoadingContent"),
        contentAlignment = Alignment.Center
    ) {
        StephenKingCircularProgressIndicator(Modifier.testTag("BookInProgress"))
    }
}

@Composable
fun BookListErrorContent(
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
fun BookListSuccessContent(
    content: List<Book>,
    onBookClick: (String) -> Unit,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .padding(12.dp)
            .testTag("BookListSuccessContent")
    ) {
        if (content.isEmpty()) {
            Text(
                text = stringResource(R.string.no_books_found),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("NoBooksFound")
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .testTag("BookListLazyColumn"),
            ) {
                items(content, key = { item -> item.id }) { book ->
                    BookListItem(
                        content = book,
                        onBookClick = onBookClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun BookListItem(
    content: Book,
    onBookClick: (String) -> Unit,
    modifier: Modifier,
) {
    Card(
        modifier = modifier
            .defaultMinSize(0.dp, 56.dp)
            .padding(4.dp)
            .clickable { onBookClick(content.id.toString()) }
            .testTag("BookListItem${content.id}"),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = content.title,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = content.year.toString(),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
