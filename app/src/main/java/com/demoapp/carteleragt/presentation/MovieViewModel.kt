package com.demoapp.carteleragt.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.demoapp.carteleragt.core.Resource
import com.demoapp.carteleragt.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

// capa presentacion:  el repositorio le manda la data al viewModel.
class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    // se comunica con el repositorio.
    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        // tenemos 3 tipos de estado. carga, exito y error.
        // emite el valor de carga.
        emit(Resource.Loading())
        // llamada al servidor.
        try {
            emit(
                Resource.Success(
                    Triple(
                        repo.getUpcomingMovies(),
                        repo.getTopRatedMovies(),
                        repo.getPopularMovies()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}