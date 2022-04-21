package com.example.tmdbmovieapplication.repository

import android.content.Context
import com.example.tmdbmovieapplication.TMDBMovieApplication
import com.example.tmdbmovieapplication.api.MovieTMDBService
import com.example.tmdbmovieapplication.data.Movie
import com.example.tmdbmovieapplication.room.MovieDatabase
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class MovieRepository @Inject constructor(
    val moviesTMDBService: MovieTMDBService,
    val apiKey: String,
    context: Context
) {
    private var moviesDatabase: MovieDatabase = MovieDatabase.getInstance(context)

    suspend fun getMovies(): List<Movie> {
        var movies = getMoviesFromDb()
        if (movies.isEmpty()) {
            movies = getMoviesFromApi()
        }
        return movies
    }

//    Response{protocol=h2, code=401, message=, url=https://api.themoviedb.org/3/movie/popular?api_key=https%3A%2F%2Fapi.themoviedb.org%2F3%2F}

    suspend fun getMoviesFromApi(): List<Movie> {
        val response = moviesTMDBService.getPopularMovies(apiKey)
        lateinit var movies: List<Movie>
        if (response.body() != null) {
            movies = response.body()!!.movies
        }
        saveMoviesInDb(movies)
        return movies
    }

    suspend fun getMoviesFromDb(): List<Movie> {
        return moviesDatabase.movieDao().getMovies()
    }

    suspend fun saveMoviesInDb(movies: List<Movie>) {
        moviesDatabase.movieDao().insertMovies(movies)
    }
}