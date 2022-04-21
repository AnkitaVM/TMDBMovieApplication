package com.example.tmdbmovieapplication.ui.popularmovies

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.tmdbmovieapplication.MoviesActivity
import com.example.tmdbmovieapplication.R
import com.example.tmdbmovieapplication.TMDBMovieApplication
import com.example.tmdbmovieapplication.data.Movie
import com.example.tmdbmovieapplication.databinding.PopularMoviesFragmentBinding
import com.example.tmdbmovieapplication.room.MovieDatabase
import java.text.FieldPosition
import javax.inject.Inject

class PopularMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = PopularMoviesFragment()
    }

    private lateinit var movieAdapter: PopularMoviesAdapter
    private lateinit var viewModel: PopularMoviesViewModel
    private lateinit var popularMoviesFragmentBinding: PopularMoviesFragmentBinding
    private var movies: MutableList<Movie> = mutableListOf()
    private val movieDetailsFragment by lazy {
        MovieDetailsFragment.newInstance()
    }

    @Inject
    lateinit var movieFactory: PopularMoviesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        popularMoviesFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.popular_movies_fragment, container, false)
//        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://api.themoviedb.org/3/")
//            .build()
//        val popularMovieService = retrofit.create(MovieTMDBService::class.java)
//        val moviesDatabase = MovieDatabase.getInstance(requireContext())
//        movieFactory = PopularMoviesViewModelFactory(
//            MovieRepository(
//                popularMovieService,
//                "f60ae473c8d03fdd88e75848cea96a8e",
//                moviesDatabase
//            )
//        )

        (requireActivity().application as TMDBMovieApplication).movieAppComponent.inject(this)

//        val movieAppComponent = DaggerTMDBMoviesAppComponent.builder()
//            .appModule(AppModule(requireContext()))
//            .movieTMDBServiceModule(MovieTMDBServiceModule("https://api.themoviedb.org/3/"))
//            .movieModule(MovieModule("f60ae473c8d03fdd88e75848cea96a8e"))
//            .build()
//            .inject(this)

        viewModel = ViewModelProvider(
            requireActivity(),
            movieFactory
        ).get(PopularMoviesViewModel::class.java)
        setRecyclerView()
        loadPopularMovies()
        return popularMoviesFragmentBinding.root
    }

    private fun setRecyclerView() {
        popularMoviesFragmentBinding.rvPopularMovies.layoutManager = LinearLayoutManager(activity)
        movieAdapter = PopularMoviesAdapter(movies, MovieClickListenerImpl())
        popularMoviesFragmentBinding.rvPopularMovies.adapter = movieAdapter
    }

    private fun loadPopularMovies() {

        val m = viewModel.getPopularMovies()
        m.observe(viewLifecycleOwner) {
            if (it != null) {
                movies.clear()
                movies.addAll(it)
                movieAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, getString(R.string.no_data), Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class MovieClickListenerImpl : PopularMoviesAdapter.MovieClickListener {
        override fun movieItemClick(moviePosition: Int) {

            Toast.makeText(
                this@PopularMoviesFragment.context,
                "Movie position: $moviePosition",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.setCurrentPosition(moviePosition)
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, movieDetailsFragment)
                .addToBackStack(MovieDetailsFragment::class.simpleName)
                .commit()


//             findNavController().navigate(R.id.action_navigation_movie_list_to_navigation_movie_details)
//            val navHostFragment =
//                fragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
//            val navController = navHostFragment.navController
//            navController.navigateUp()
//            navController.navigate(R.id.action_navigation_movie_list_to_navigation_movie_details)
//            Handler().postDelayed({
//                (activity as MoviesActivity).navigateNext()
//            },1000)
//
//            (activity as MoviesActivity).navi
            //(activity as MoviesActivity).navigateNext()


//            val navHostFragment =
////                parentFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
////            val navController = navHostFragment.navController
        }

    }

}