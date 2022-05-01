package com.mehmetpeker.nasaroverproject.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY&page=1
    @GET("rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY")
    fun getCuriosityRoverPhoto(@Query(value = "page") page:Int){}
}