package com.mmag.stephenking.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class SafeService {
    suspend fun <T> safeApiCall(request: suspend () -> Response<T>): SafeApiResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val apiResponse: Response<T> = request()
                if (apiResponse.isSuccessful) {
                    SafeApiResponse.Success(apiResponse.body())
                } else {
                    //TODO if the API has custom error messages, parse them here
                    getDefaultError(apiResponse)
                }
            } catch (e: HttpException) {
                SafeApiResponse.Error(
                    if (!e.message().isNullOrBlank() && e.message() != "Response.error()")
                        e.message()
                    else
                        "Something went wrong"
                )
            } catch (e: IOException) {
                SafeApiResponse.Error("Please check your network connection")
            } catch (e: Exception) {
                SafeApiResponse.Error("Something went wrong")
            }
        }
    }

    private fun <T> getDefaultError(apiResponse: Response<T>): SafeApiResponse.Error<T> =
        when (apiResponse.code()) {
            in 299..300 -> {
                SafeApiResponse.Error(
                    "Must redirect the request, redirection error",
                    null
                )
            }

            in 399..500 -> {
                when (apiResponse.code()) {
                    400 -> SafeApiResponse.Error("Bad request")
                    401 -> SafeApiResponse.Error("Unauthorized")
                    403 -> SafeApiResponse.Error("Forbidden")
                    404 -> SafeApiResponse.Error("Not Found")
                    else -> SafeApiResponse.Error("Request error, code: ${apiResponse.code()}")
                }
            }

            in 499..600 -> {
                SafeApiResponse.Error("Server error, code: ${apiResponse.code()}", null)
            }

            else -> {
                SafeApiResponse.Error("Unknown error, code: ${apiResponse.code()}", null)
            }
        }
}