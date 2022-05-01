package com.mehmetpeker.nasaroverproject.di

import com.mehmetpeker.nasaroverproject.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideApiService(){

    }

    @Provides
    fun provideOkHttpClient(){

    }
    @Provides
    fun provideRetrofit(){

    }

}