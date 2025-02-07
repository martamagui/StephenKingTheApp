package com.mmag.stephenking.data

import com.mmag.stephenking.data.network.SafeApiResponse
import com.mmag.stephenking.data.network.SafeService
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doReturn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class SafeServiceTest {

    private lateinit var safeService: SafeService

    @Before
    fun setUp() {
        safeService = object : SafeService() {}
    }

    @Test
    fun `safeApiCall returns success when API call is successful`() = runTest {
        val apiResponse: Response<String> = mock()
        `when`(apiResponse.isSuccessful).thenReturn(true)
        `when`(apiResponse.body()).thenReturn("Success")

        val result = safeService.safeApiCall { apiResponse }

        assert(result is SafeApiResponse.Success)
        assertEquals("Success", (result as SafeApiResponse.Success).data)
    }

    @Test
    fun `safeApiCall returns error for HTTP error response code 299`() = runTest {
        val apiResponse: Response<String> = mock()
        `when`(apiResponse.isSuccessful).thenReturn(false)
        `when`(apiResponse.code()).thenReturn(299)

        val result = safeService.safeApiCall { apiResponse }

        assert(result is SafeApiResponse.Error)
        assertEquals("Must redirect the request, redirection error", (result as SafeApiResponse.Error).errorMessage)
    }

    @Test
    fun `safeApiCall returns error for HTTP error response code 400`() = runTest {
        val apiResponse: Response<String> = mock()
        `when`(apiResponse.isSuccessful).thenReturn(false)
        `when`(apiResponse.code()).thenReturn(400)

        val result = safeService.safeApiCall { apiResponse }

        assert(result is SafeApiResponse.Error)
        assertEquals("Bad request", (result as SafeApiResponse.Error).errorMessage)
    }

    @Test
    fun `safeApiCall returns error for HTTP error response code 401`() = runTest {
        val apiResponse: Response<String> = mock()
        `when`(apiResponse.isSuccessful).thenReturn(false)
        `when`(apiResponse.code()).thenReturn(401)

        val result = safeService.safeApiCall { apiResponse }

        assert(result is SafeApiResponse.Error)
        assertEquals("Unauthorized", (result as SafeApiResponse.Error).errorMessage)
    }

    @Test
    fun `safeApiCall returns error for HTTP error response code 500`() = runTest {
        val apiResponse: Response<String> = mock()
        `when`(apiResponse.isSuccessful).thenReturn(false)
        `when`(apiResponse.code()).thenReturn(500)

        val result = safeService.safeApiCall { apiResponse }

        assert(result is SafeApiResponse.Error)
        assertEquals("Server error, code: ${apiResponse.code()}", (result as SafeApiResponse.Error).errorMessage)
    }

    @Test
    fun `safeApiCall returns error for HTTP error response not registered code`() = runTest {
        val apiResponse: Response<String> = mock()
        `when`(apiResponse.isSuccessful).thenReturn(false)
        `when`(apiResponse.code()).thenReturn(601)

        val result = safeService.safeApiCall { apiResponse }

        assert(result is SafeApiResponse.Error)
        assertEquals("Unknown error, code: ${apiResponse.code()}", (result as SafeApiResponse.Error).errorMessage)
    }

    @Test
    fun `safeApiCall returns error for HTTP error response 404`() = runTest {
        val apiResponse: Response<String> = mock()
        `when`(apiResponse.isSuccessful).thenReturn(false)
        `when`(apiResponse.code()).thenReturn(404)

        val result = safeService.safeApiCall { apiResponse }

        assert(result is SafeApiResponse.Error)
        assertEquals("Not Found", (result as SafeApiResponse.Error).errorMessage)
    }


    @Test
    fun `safeApiCall catches HttpException`() = runTest {
        val message = "Http error "
        val httpExceptionFunction: suspend (text: String) -> Response<String> = { text ->
            val exception = mock<HttpException>()
            `when`(exception.message()).doReturn(text)
            throw exception
        }

        val result = safeService.safeApiCall { httpExceptionFunction(message) }

        assert(result is SafeApiResponse.Error<*>)
        assertEquals(message, (result as SafeApiResponse.Error<*>).errorMessage)
    }

    @Test
    fun `safeApiCall catches IOException`() = runTest {
        val message = "IO"
        val exceptionFunction: suspend (text: String) -> Response<String> = { text ->
            val exception = IOException(text)
            throw exception
        }

        val result = safeService.safeApiCall { exceptionFunction(message) }

        assert(result is SafeApiResponse.Error<*>)
        assertEquals(
            "Please check your network connection",
            (result as SafeApiResponse.Error<*>).errorMessage
        )
    }

    @Test
    fun `safeApiCall catches unknown exceptions`() = runTest {
        val message = "unknown exception"
        val exceptionFunction: suspend (text: String) -> Response<String> = { text ->
            val exception = Exception(text)
            throw exception
        }

        val result = safeService.safeApiCall { exceptionFunction(message) }

        assert(result is SafeApiResponse.Error<*>)
        assertEquals("Something went wrong", (result as SafeApiResponse.Error<*>).errorMessage)
    }
}
