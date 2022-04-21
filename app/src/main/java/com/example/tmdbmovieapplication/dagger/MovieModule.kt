package com.example.tmdbmovieapplication.dagger

import android.content.Context
import androidx.room.PrimaryKey
import com.example.tmdbmovieapplication.api.MovieTMDBService
import com.example.tmdbmovieapplication.repository.MovieRepository
import com.example.tmdbmovieapplication.ui.popularmovies.PopularMoviesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MovieModule(private val apiKey: String) {
    @Provides
    fun provideMovieViewModelFactory(movieRepository: MovieRepository): PopularMoviesViewModelFactory {
        return PopularMoviesViewModelFactory(movieRepository)
    }

    @Provides
    fun provideMovieRepository(
        moviesTMDBService: MovieTMDBService,
        @Named("api_key")
        apiKey: String,
        context: Context
    ): MovieRepository {
        return MovieRepository(moviesTMDBService, apiKey, context)
    }

    @Named("api_key")
    @Provides
    fun provideApiKey(): String {
        return apiKey
    }
}