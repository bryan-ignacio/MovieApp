package com.demoapp.carteleragt.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.demoapp.carteleragt.core.Resource
import com.demoapp.carteleragt.data.model.Movie
import com.demoapp.carteleragt.data.remote.RemoteMovieDataSource
import com.demoapp.carteleragt.presentation.MovieViewModel
import com.demoapp.carteleragt.presentation.MovieViewModelFactory
import com.demoapp.carteleragt.repository.MovieRepositoryImplement
import com.demoapp.carteleragt.repository.RetrofitClient
import com.demoapp.carteleragt.ui.movie.adapters.MovieAdapter
import com.demoapp.carteleragt.ui.movie.adapters.concat.PopularConcatAdapter
import com.demoapp.carteleragt.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.demoapp.carteleragt.ui.movie.adapters.concat.UpcomingConcatAdapter
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieBinding

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    // inicializamos binding.
    private lateinit var binding: FragmentMovieBinding


    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImplement(
                RemoteMovieDataSource(
                    RetrofitClient.webservice
                )
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // mandamos la vista al binding.
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        //acceder a los elementos que se encuentran en el layout vistas.
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    // mostrar progressbar.
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    // ocultar progressbar.
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }
                    binding.rvMovies.adapter = concatAdapter
                }

                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
//        Log.d("Movie", "onMovieClick: $movie")
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date,
        )
        findNavController().navigate(action)
    }
}