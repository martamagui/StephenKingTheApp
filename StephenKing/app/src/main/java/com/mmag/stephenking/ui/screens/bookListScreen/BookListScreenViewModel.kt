package com.mmag.stephenking.ui.screens.bookListScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.repository.BookRepository
import com.mmag.stephenking.ui.model.BookItem
import com.mmag.stephenking.ui.model.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListScreenViewModel @Inject constructor(
    private val bookRepository: BookRepository,
) : ViewModel() {

    private var _bookListScreenSate: MutableStateFlow<StephenKingResponse<List<BookItem>>> =
        MutableStateFlow(StephenKingResponse.Loading())
    val bookListScreenSate: StateFlow<StephenKingResponse<List<BookItem>>> get() = _bookListScreenSate


    init {
        retrieveBooks()
    }

    fun retrieveBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getBookListResponse().collect { response ->
                when (response) {
                    is StephenKingResponse.Error -> _bookListScreenSate.value =
                        StephenKingResponse.Error(response.errorMessage)

                    is StephenKingResponse.Loading -> _bookListScreenSate.value =
                        StephenKingResponse.Loading()

                    is StephenKingResponse.Success -> {
                        val uiModelList: List<BookItem> =
                            response.data?.map { it.toUIModel() } ?: emptyList()
                        _bookListScreenSate.value = StephenKingResponse.Success(uiModelList)
                    }
                }
            }
        }
    }
}