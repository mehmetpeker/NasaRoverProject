package com.mehmetpeker.nasaroverproject.util

import retrofit2.Response


suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {

    try {
        val apiCallResult = apiCall()
        if (apiCallResult.isSuccessful) {
            apiCallResult.body()?.let {
                return Resource.Success(data = it)
            }
        } else {
            apiCallResult.errorBody()?.let {
                return Resource.Error(message = it.toString())
            }
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    return Resource.Error(message = "Something went wrong!")
}
