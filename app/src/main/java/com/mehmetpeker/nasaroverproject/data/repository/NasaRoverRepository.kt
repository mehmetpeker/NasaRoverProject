package com.mehmetpeker.nasaroverproject.data.repository

import com.mehmetpeker.nasaroverproject.data.datasource.RoverPagingSource

interface NasaRoverRepository {

    fun getRoverPagingSource(roverName: String,sol:Int,camera: String,apiKey:String) :RoverPagingSource
}