package com.example.tmdbmovieapplication

import android.app.Application
import com.example.tmdbmovieapplication.dagger.*

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