package com.mmag.stephenking.data.network

import com.mmag.stephenking.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiModuleTest {

    @Test
    fun `test provideOkHttpClient returns OkHttpClient with body level logs`() {
        val client = ApiModule.provideOkHttpClient()

        assert(client is OkHttpClient)

        val interceptors = client.interceptors
        val loggingInterceptor = interceptors.find { it is HttpLoggingInterceptor } as? HttpLoggingInterceptor

        assert(loggingInterceptor != null)
        assertEquals(HttpLoggingInterceptor.Level.BODY, loggingInterceptor?.level)
    }

    @Test
    fun `test provideRetrofit returns GsonConverterFactory`() {
        val mockClient = mock(OkHttpClient::class.java)
        val retrofit = ApiModule.provideRetrofit(mockClient)

        assertEquals(BuildConfig.BASE_URL, retrofit.baseUrl().toString())

        val converterFactories = retrofit.converterFactories()
        assert(converterFactories.any { it is GsonConverterFactory })
    }

    @Test
    fun `test provideService returns ApiService`() {
        val mockRetrofit = mock(Retrofit::class.java)
        val mockService = mock(ApiService::class.java)

        `when`(mockRetrofit.create(ApiService::class.java)).thenReturn(mockService)

        val service = ApiModule.provideService(mockRetrofit)

        assertEquals(mockService, service)
    }
}