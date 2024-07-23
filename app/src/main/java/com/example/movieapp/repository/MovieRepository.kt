package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList


// metodos que se van a utilizar en la implementacion.
interface MovieRepository {

    // como no sabemos en que momento va responder el servidor, se utiliza suspend.
    suspend fun getUpcomingMovies(): MovieList

    suspend fun getTopRatedMovies(): MovieList

    suspend fun getPopularMovies(): MovieList

}