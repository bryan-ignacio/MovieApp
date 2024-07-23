package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieList


// metodos finales para traer la info desde el servidor.
class MovieDataSource {

    fun getUpcomingMovies(): MovieList {

        return MovieList()
    }

    fun getTopRatedMovies(): MovieList {
        return MovieList()
    }

    fun getPopularMovies(): MovieList {
        return MovieList()
    }

}