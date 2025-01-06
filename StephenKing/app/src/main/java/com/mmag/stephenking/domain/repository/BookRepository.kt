package com.mmag.stephenking.domain.repository

import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBookListResponse(): Flow<StephenKingResponse<List<Book>>>
    suspend fun getBookDetailResponse(bookId:String): Flow<StephenKingResponse<Book>>
}