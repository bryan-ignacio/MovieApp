package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.remote.MovieDataSource
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.repository.MovieRepositoryImplement
import com.example.movieapp.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    // inicializamos binding.
    private lateinit var binding: FragmentMovieBinding


    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImplement(
                MovieDataSource(
                    RetrofitClient.webservice
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // mandamos la vista al binding.
        binding = FragmentMovieBinding.bind(view)
        //acceder a los elementos que se encuentran en el layout vistas.
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }

                is Resource.Success -> {
                    Log.d(
                        "LiveData",
                        "Upcoming: ${result.data.first}\nTopRated:${result.data.second}\nPopular: ${result.data.third}"
                    )
                }

                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })
    }
}