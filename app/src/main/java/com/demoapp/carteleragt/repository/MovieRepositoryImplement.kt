package com.demoapp.carteleragt.repository

import com.demoapp.carteleragt.core.InternetCheck
import com.demoapp.carteleragt.data.local.LocalMovieDataSource
import com.demoapp.carteleragt.data.model.MovieList
import com.demoapp.carteleragt.data.model.toMovieEntity
import com.demoapp.carteleragt.data.remote.RemoteMovieDataSource

// implementar la interface movieRepository
class MovieRepositoryImplement(
    private val dataSource: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    // implementar las funciones abstractas del MovieRepository
    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }

    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }

    }
}