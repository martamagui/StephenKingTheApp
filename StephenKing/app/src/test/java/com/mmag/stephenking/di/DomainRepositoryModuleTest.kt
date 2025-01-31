package com.mmag.stephenking.di

import com.mmag.stephenking.data.network.repository.BookServiceRepository
import com.mmag.stephenking.domain.repository.BookRepository
import com.mmag.stephenking.domain.repository.BookRepositoryImpl
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.*

class DomainRepositoryModuleTest {

    @Test
    fun `test providesBookRepository returns BookRepositoryImpl`() {
        val mockBookServiceRepository = mock(BookServiceRepository::class.java)

        val bookRepository: BookRepository =
            DomainRepositoryModule.providesBookRepository(mockBookServiceRepository)

        assertTrue(bookRepository is BookRepositoryImpl)
    }
}