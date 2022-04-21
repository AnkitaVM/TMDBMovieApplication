package com.example.tmdbmovieapplication

import android.app.Application
import androidx.room.Room
import com.example.tmdbmovieapplication.dagger.*
import com.example.tmdbmovieapplication.room.MovieDatabase
import dagger.internal.DaggerGenerated

class TMDBMovieApplication : Application() {

    lateinit var movieAppComponent: TMDBMoviesAppComponent
    override fun onCreate() {
        super.onCreate()
        movieAppComponent = DaggerTMDBMoviesAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .movieTMDBServiceModule(MovieTMDBServiceModule(BuildConfig.BASE_URL))
            .movieModule(MovieModule(BuildConfig.API_KEY))
            .build()

    }
}