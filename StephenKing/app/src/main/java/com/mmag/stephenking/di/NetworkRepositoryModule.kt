package com.mmag.stephenking.di

import com.mmag.stephenking.data.network.ApiService
import com.mmag.stephenking.data.network.repository.BookServiceRepository
import com.mmag.stephenking.data.network.repository.BookServiceRepositoryRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkRepositoryModule {
    @Singleton
    @Provides
    fun providesBookBookServiceRepository(service: ApiService): BookServiceRepository =
        BookServiceRepositoryRetrofit(service)
}