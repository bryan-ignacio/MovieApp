package com.example.movieapp.data.remote

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.repository.WebService


// metodos finales para traer la info desde el servidor.
// vamos a crear la consulta al servidor para traer la data.
class MovieDataSource(private val webservice: WebService) {

    // colocamos suspend porque el webservie tambien es suspend.
    suspend fun getUpcomingMovies(): MovieList = webservice.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webservice.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webservice.getPopularMovies(AppConstants.API_KEY)

}