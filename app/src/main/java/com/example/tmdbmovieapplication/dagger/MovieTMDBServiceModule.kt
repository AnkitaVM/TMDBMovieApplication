package com.example.tmdbmovieapplication.dagger

import com.example.tmdbmovieapplication.api.MovieTMDBService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class MovieTMDBServiceModule (private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideRetrofitInstance() : Retrofit {
       return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun providePopularMoviesServiceInstance(retrofit: Retrofit): MovieTMDBService {
        return retrofit.create(MovieTMDBService::class.java)
    }


    @Provides
    fun provideBaseUrl(): String {
        return baseUrl
    }

}