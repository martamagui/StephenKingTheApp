package com.mmag.stephenking.ui.screens.bookListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.useCases.GetBookListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListScreenViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase,
) : ViewModel() {

    private var _bookListScreenSate: MutableStateFlow<StephenKingResponse<List<Book>>> =
        MutableStateFlow(StephenKingResponse.Loading())
    val bookListScreenSate: StateFlow<StephenKingResponse<List<Book>>> get() = _bookListScreenSate


    init {
        retrieveBooks()
    }

    fun retrieveBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            getBookListUseCase.invoke().collect { response ->
                 _bookListScreenSate.value = response
            }
        }
    }
}