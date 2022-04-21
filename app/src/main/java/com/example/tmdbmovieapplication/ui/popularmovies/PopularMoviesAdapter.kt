package com.example.tmdbmovieapplication.ui.popularmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbmovieapplication.R
import com.example.tmdbmovieapplication.data.Movie
import com.example.tmdbmovieapplication.databinding.LayoutItemMovieBinding

class PopularMoviesAdapter(
    private val movies: List<Movie>,
    var movieClickListener: MovieClickListener
) :
    RecyclerView.Adapter<PopularMoviesAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemMovieBinding: LayoutItemMovieBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_movie, parent, false)

        return MovieHolder(layoutItemMovieBinding, movieClickListener)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindView(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieHolder(
        private val layoutItemMovieBinding: LayoutItemMovieBinding,
        val movieClickListener: MovieClickListener,

        ) :
        RecyclerView.ViewHolder(layoutItemMovieBinding.root), View.OnClickListener {
        init {
            layoutItemMovieBinding.root.setOnClickListener(this)
        }

        fun bindView(movie: Movie) {
            layoutItemMovieBinding.txtMovieName.text = movie.title
            layoutItemMovieBinding.txtReleaseDate.text = movie.releaseDate
            val posterURL = "https://image.tmdb.org/t/p/w500" + movie.posterPath
            Glide.with(layoutItemMovieBinding.imgMovie.context)
                .load(posterURL)
                .into(layoutItemMovieBinding.imgMovie)
        }

        override fun onClick(p0: View?) {
            val position = this.adapterPosition
            movieClickListener.movieItemClick(position)
        }

    }

    interface MovieClickListener {
        fun movieItemClick(position: Int)
    }

}