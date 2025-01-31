package com.mmag.stephenking.di

import com.mmag.stephenking.domain.repository.BookRepository
import com.mmag.stephenking.domain.useCases.GetBookDetailUseCase
import com.mmag.stephenking.domain.useCases.GetBookListUseCase
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.*

class DomainUseCasesModuleTest {

    @Test
    fun `test providesGetBookListUseCase returns GetBookListUseCase`() {
        val mockBookRepository = mock(BookRepository::class.java)

        val useCase = DomainUseCasesModule.providesGetBookListUseCase(mockBookRepository)

        assertTrue(useCase is GetBookListUseCase)
    }

    @Test
    fun `test providesGetBookDetailUseCase returns GetBookDetailUseCase`() {
        val mockBookRepository = mock(BookRepository::class.java)

        val useCase = DomainUseCasesModule.providesGetBookDetailUseCase(mockBookRepository)

        assertTrue(useCase is GetBookDetailUseCase)
    }
}