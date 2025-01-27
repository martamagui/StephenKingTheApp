package com.mmag.stephenking.ui.screens.bookDetailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
    onBackPressed: () -> Unit,
) {
    LaunchedEffect(rememberCoroutineScope()) {
        viewModel.getBookDetail(bookId)
    }

    val uiState by viewModel.bookDetailScreenState.collectAsState()

    Scaffold(
        topBar = { BookDetailTopAppBar(onBackPressed) },
        modifier = Modifier
            .fillMaxSize()
            .testTag("BookDetailScreen: $bookId")
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

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
                    } else {
                        BookDetailSuccessScreen(uiState.data!!, Modifier.fillMaxSize())
                    }
                }

                is StephenKingResponse.Error -> BookDetailErrorContent(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailTopAppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.book_detail_back_content_description)
                )
            }
        },
        title = {},
        modifier = Modifier.testTag("BookDetailTopAppBar")
    )
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
            .padding(12.dp)
            .testTag("BookDetailSuccessScreen")
    ) {
        if (maxWidth < 480.dp) {
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
    LazyColumn(modifier = modifier.testTag("BookDetailSuccessScreenSmall")) {
        item {
            BookDetailHeader(Modifier.fillMaxWidth(), book)
        }

        item {
            if (book.villains.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.book_detail_villains_title),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.testTag("BookDetailVillainsTitle")
                )
            }
        }

        items(book.villains, key = { item -> item.name }) { villain ->
            Text(
                text = villain.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.testTag("BookDetailVillain ${villain.name}")
            )
        }

        item {
            if (book.notes.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.book_detail_notes_title),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.testTag("BookDetailNotesTitle")
                )
            }
        }

        items(book.notes, key = { item -> item }) { note ->
            Text(
                text = note,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.testTag("BookDetailNote $note")
            )
        }
    }
}

@Composable
fun BookDetailSuccessScreenBig(
    book: Book,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .testTag("BookDetailSuccessScreenSmall")
    ) {

        BookDetailHeader(Modifier.fillMaxWidth(), book)

        Row(verticalAlignment = Alignment.Top) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                if (book.villains.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.book_detail_villains_title),
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.testTag("BookDetailVillainsTitle")
                    )
                    book.villains.forEach { villain ->
                        Text(
                            text = villain.name,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.testTag("BookDetailVillain ${villain.name}")
                        )
                    }
                }
            }

            if (book.notes.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.book_detail_notes_title),
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.testTag("BookDetailNotesTitle")
                    )

                    book.notes.forEach { note ->
                        Text(
                            text = note,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.testTag("BookDetailNote $note")
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BookDetailHeader(modifier: Modifier, book: Book) {
    Column(
        modifier
            .testTag("BookDetailHeader")
    ) {
        Text(
            text = book.title,
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = stringResource(R.string.book_detail_year, book.year.toString()),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.book_detail_publisher, book.publisher),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.book_detail_isbn, book.iSBN),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.book_detail_pages, book.pages),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}