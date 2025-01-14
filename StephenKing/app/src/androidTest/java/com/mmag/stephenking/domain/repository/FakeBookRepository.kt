package com.mmag.stephenking.domain.repository

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeBookRepository : BookRepository {

    var fakeListState: MutableStateFlow<StephenKingResponse<List<Book>>> = MutableStateFlow(
        StephenKingResponse.Loading()
    )
    var fakeDetailState: MutableStateFlow<StephenKingResponse<Book>> = MutableStateFlow(
        StephenKingResponse.Loading()
    )

    override suspend fun getBookListResponse(): Flow<StephenKingResponse<List<Book>>> {
        return fakeListState
    }

    override suspend fun getBookDetailResponse(bookId: String): Flow<StephenKingResponse<Book>> {
        return fakeDetailState
    }
}
