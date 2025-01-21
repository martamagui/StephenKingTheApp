package com.mmag.stephenking.ui.screens.bookDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import com.mmag.stephenking.domain.useCases.GetBookDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailScreenViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase
) : ViewModel() {

    private var _bookDetailScreenState: MutableStateFlow<StephenKingResponse<Book>> =
        MutableStateFlow(StephenKingResponse.Loading())
    val bookDetailScreenState: StateFlow<StephenKingResponse<Book>> get() = _bookDetailScreenState


    fun getBookDetail(id: String) = viewModelScope.launch {
        getBookDetailUseCase.invoke(id).collect { response ->
            _bookDetailScreenState.value = response
        }
    }
}