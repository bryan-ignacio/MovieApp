package com.demoapp.carteleragt.data.local

import com.demoapp.carteleragt.data.model.MovieEntity
import com.demoapp.carteleragt.data.model.MovieList
import com.demoapp.carteleragt.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter {
            it.movie_type == "upcoming"
        }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter {
            it.movie_type == "toprated"
        }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter {
            it.movie_type == "popular"
        }.toMovieList()
    }

    suspend fun saveMovie(movie:MovieEntity) {
        movieDao.saveMovie(movie)
    }
}