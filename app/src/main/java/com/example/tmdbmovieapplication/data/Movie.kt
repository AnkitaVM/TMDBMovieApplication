package com.example.tmdbmovieapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "movie_overview")
    @SerializedName("overview")
    val overview: String?,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "movie_title")
    @SerializedName("title")
    val title: String?
)
