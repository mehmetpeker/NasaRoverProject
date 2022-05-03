package com.mehmetpeker.nasaroverproject.api

import com.mehmetpeker.nasaroverproject.data.model.RoverPhotoResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("rovers/{rover}/photos")
    @JvmSuppressWildcards
    suspend fun getRoverPhotoByName(@Path("rover") rover:String,
                                    @QueryMap map : Map<String, Any>)
    :Response<RoverPhotoResponseModel>

}