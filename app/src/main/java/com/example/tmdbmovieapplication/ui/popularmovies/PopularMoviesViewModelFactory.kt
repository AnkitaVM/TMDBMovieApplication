package com.example.tmdbmovieapplication.ui.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbmovieapplication.repository.MovieRepository
import javax.inject.Inject

class PopularMoviesViewModelFactory (private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(movieRepository) as T
    }
}