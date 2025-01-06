package com.mmag.stephenking.domain.repository

import com.mmag.stephenking.data.network.SafeApiResponse
import com.mmag.stephenking.data.network.repository.BookServiceRepository
import com.mmag.stephenking.domain.model.mapper.toDomainModel
import com.mmag.stephenking.domain.model.Book
import com.mmag.stephenking.domain.model.StephenKingResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookServiceRepository: BookServiceRepository,
) : BookRepository {

    override suspend fun getBookListResponse(): Flow<StephenKingResponse<List<Book>>> = flow {
        emit(StephenKingResponse.Loading())

        when (val response = bookServiceRepository.getBookListResponse()) {
            is SafeApiResponse.Success -> {
                val books = response.data?.bookList?.map { it.toDomainModel() } ?: emptyList()
                emit(StephenKingResponse.Success(books))
            }

            is SafeApiResponse.Error -> {
                emit(StephenKingResponse.Error(response.errorMessage))
            }
        }
    }


    override suspend fun getBookDetailResponse(bookId: String): Flow<StephenKingResponse<Book>> =
        flow {
            emit(StephenKingResponse.Loading())

            when (val response = bookServiceRepository.getBookDetailResponse(bookId)) {
                is SafeApiResponse.Success -> {
                    val book = response.data?.bookDetail?.toDomainModel()
                    emit(StephenKingResponse.Success(book))
                }

                is SafeApiResponse.Error -> {
                    emit(StephenKingResponse.Error(response.errorMessage))
                }
            }
        }
}