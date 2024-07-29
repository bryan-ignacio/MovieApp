package com.demoapp.carteleragt.repository

import com.demoapp.carteleragt.application.AppConstants
import com.demoapp.carteleragt.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Es el encargado de consumir el servicio, con retrofit para traer la data del servidor.
interface WebService {

    // toma la base url y le agrega el endpoint.
    @GET("movie/upcoming")
    // como no sabemos en que momento va responder el servidor, se utiliza suspend.
    // el query tiene que tener el nombre que le da el servidor. en este caso la query del servidor es "api_key"
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

}


object RetrofitClient {
    // la inicializa en el momento que se llama al webService.
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}