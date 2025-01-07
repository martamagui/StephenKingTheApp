package com.mmag.stephenking.di

import com.mmag.stephenking.domain.repository.BookRepository
import com.mmag.stephenking.domain.useCases.GetBookDetailUseCase
import com.mmag.stephenking.domain.useCases.GetBookListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainUseCasesModule {

    @Provides
    fun providesGetBookListUseCase(bookRepository: BookRepository): GetBookListUseCase =
        GetBookListUseCase(bookRepository)

    @Provides
    fun providesGetBookDetailUseCase(bookRepository: BookRepository): GetBookDetailUseCase =
        GetBookDetailUseCase(bookRepository)
}