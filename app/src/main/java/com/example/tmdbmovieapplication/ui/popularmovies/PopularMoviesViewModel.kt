package com.example.tmdbmovieapplication.ui.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbmovieapplication.data.Movie
import com.example.tmdbmovieapplication.repository.MovieRepository
import java.text.FieldPosition

class PopularMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var movies: List<Movie>

    fun getPopularMovies() = liveData {
        movies = movieRepository.getMovies()
        emit(movies)
    }

    private var currentPosition: Int = 0
    fun setCurrentPosition(position: Int) {
        currentPosition = position
    }

    fun getSelectedMovie(): Movie {
        return movies[currentPosition]
    }

}