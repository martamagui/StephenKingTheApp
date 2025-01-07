package com.mmag.stephenking.ui.screens.bookListScreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.mmag.stephenking.ui.commonComponents.BookListListScreenTopAppBar
import com.mmag.stephenking.ui.commonComponents.StephenKingCircularProgressIndicator

@Composable
fun BookListScreen(
    viewModel: BookListScreenViewModel = hiltViewModel(),
    onBookClick: (String) -> Unit,
) {
    val uiState by viewModel.bookListScreenSate.collectAsState()
    Scaffold(
        topBar = { BookListListScreenTopAppBar() }
    ) { paddingValues ->
        when (uiState) {
            is StephenKingResponse.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.errorMessage
                            ?: stringResource(R.string.default_error_message)
                    )
                }
            }

            is StephenKingResponse.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    StephenKingCircularProgressIndicator(Modifier.testTag("BookInProgress"))
                }
            }

            is StephenKingResponse.Success -> BookListContent(
                content = uiState.data?: emptyList(),
                onBookClick = { id -> onBookClick(id) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
        }
    }
}

@Composable
fun BookListContent(
    content: List<Book>,
    onBookClick: (String) -> Unit,
    modifier: Modifier,
) {
    Box(modifier = modifier.padding(12.dp)) {
            LazyColumn(
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(content) { book ->
                    Text(text = book.title, Modifier.clickable { onBookClick(book.id.toString()) })
                }
            }
    }
}
