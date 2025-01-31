package com.mmag.stephenking.di

import com.mmag.stephenking.data.network.ApiService
import com.mmag.stephenking.data.network.repository.BookServiceRepository
import com.mmag.stephenking.data.network.repository.BookServiceRepositoryRetrofit
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.*

class NetworkRepositoryModuleTest {

    @Test
    fun `test providesBookBookServiceRepository returns BookServiceRepositoryRetrofit`() {
        val mockApiService = mock(ApiService::class.java)

        val bookServiceRepository: BookServiceRepository =
            NetworkRepositoryModule.providesBookBookServiceRepository(mockApiService)

        assertTrue(bookServiceRepository is BookServiceRepositoryRetrofit)
    }
    
}