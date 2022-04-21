package com.example.tmdbmovieapplication.ui.popularmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tmdbmovieapplication.R
import com.example.tmdbmovieapplication.TMDBMovieApplication
import com.example.tmdbmovieapplication.databinding.FragmentMovieDetailsBinding
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : Fragment() {

    private lateinit var viewModel: PopularMoviesViewModel

    @Inject
    lateinit var movieFactory: PopularMoviesViewModelFactory
    lateinit var movieDetailsBinding: FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        movieDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        (requireActivity().application as TMDBMovieApplication).movieAppComponent.inject(this)
        viewModel = ViewModelProvider(
            requireActivity(),
            movieFactory
        ).get(PopularMoviesViewModel::class.java)
        setDetails()
        // Inflate the layout for this fragment
        return movieDetailsBinding.root
    }

    fun setDetails() {
        val movie = viewModel.getSelectedMovie()
        val posterURL = "https://image.tmdb.org/t/p/w500" + movie.posterPath
        Glide.with(movieDetailsBinding.imgMovie.context)
            .load(posterURL)
            .into(movieDetailsBinding.imgMovie)
        movieDetailsBinding.imgMovie

        movieDetailsBinding.txtMovieName.text = movie.title
        movieDetailsBinding.txtDescriptionLabel.text = movie.overview
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            MovieDetailsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }

        @JvmStatic
        fun newInstance() = MovieDetailsFragment()
    }
}