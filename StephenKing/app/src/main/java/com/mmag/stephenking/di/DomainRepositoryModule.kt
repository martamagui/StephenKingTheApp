package com.mmag.stephenking.di

import com.mmag.stephenking.data.network.repository.BookServiceRepository
import com.mmag.stephenking.domain.repository.BookRepository
import com.mmag.stephenking.domain.repository.BookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainRepositoryModule {

    @Singleton
    @Provides
    fun providesBookRepository(bookServiceRepository: BookServiceRepository): BookRepository =
        BookRepositoryImpl(bookServiceRepository)
}