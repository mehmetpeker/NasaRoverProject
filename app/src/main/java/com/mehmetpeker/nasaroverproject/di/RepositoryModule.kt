package com.mehmetpeker.nasaroverproject.di

import com.mehmetpeker.nasaroverproject.api.ApiService
import com.mehmetpeker.nasaroverproject.data.repository.NasaRoverRepository
import com.mehmetpeker.nasaroverproject.data.repository.NasaRoverRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
     @Provides
     fun provideNasaRoverRepository(apiService: ApiService):NasaRoverRepository{
         return NasaRoverRepositoryImpl(apiService)
     }
}