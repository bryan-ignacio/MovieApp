package com.example.movieapp.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieBinding

class MovieFragment : Fragment(R.layout.fragment_movie) {

// inicializamos binding.
    private lateinit var binding: FragmentMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // mandamos la vista al binding.
        binding = FragmentMovieBinding.bind(view)
        //acceder a los elementos que se encuentran en el layout vistas.

    }

}