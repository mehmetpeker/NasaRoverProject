package com.mehmetpeker.nasaroverproject.data.repository

import com.mehmetpeker.nasaroverproject.api.ApiService
import com.mehmetpeker.nasaroverproject.data.datasource.RoverPagingSource
import javax.inject.Inject

class NasaRoverRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NasaRoverRepository {


    override fun getRoverPagingSource(
        roverName: String,
        sol: Int,
        camera: String,
        apiKey: String
    ): RoverPagingSource {
        return RoverPagingSource(
            roverName = roverName,
            sol = sol,
            camera = camera,
            apiKey = apiKey,
            apiService = apiService
        )
    }

}