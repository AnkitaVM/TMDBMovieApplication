package com.example.tmdbmovieapplication.dagger

import com.example.tmdbmovieapplication.ui.popularmovies.MovieDetailsFragment
import com.example.tmdbmovieapplication.ui.popularmovies.PopularMoviesFragment
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MovieTMDBServiceModule::class, MovieModule::class])
interface TMDBMoviesAppComponent {
    fun inject(popularMoviesFragment: PopularMoviesFragment)
    fun inject(popularMoviesFragment: MovieDetailsFragment)
}