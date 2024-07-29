package com.demoapp.carteleragt.repository

import com.demoapp.carteleragt.data.model.MovieList
import com.demoapp.carteleragt.data.remote.MovieDataSource

// implementar la interface movieRepository
class MovieRepositoryImplement(private val dataSource: MovieDataSource):MovieRepository {

    // implementar las funciones abstractas del MovieRepository
    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}