package com.example.tmdbmovieapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tmdbmovieapplication.data.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getMovies(): List<Movie>

    @Insert
    suspend fun insertMovies(movies: List<Movie>)
}