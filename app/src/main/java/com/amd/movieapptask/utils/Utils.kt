package com.amd.movieapptask.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun dateFormat(date: String, input : String, output : String) : String{
        var format = SimpleDateFormat(input, Locale.getDefault())
        val newDate: Date? = format.parse(date)
        format = SimpleDateFormat(output, Locale.getDefault())
        return format.format(newDate!!)
    }

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Error(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Error(true, null, null)
                    }
                }
            }
        }
    }

}