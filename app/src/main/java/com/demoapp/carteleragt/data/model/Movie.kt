package com.demoapp.carteleragt.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Con retrofit podemos utilizar una funcion llamada serealizeName para cambiar el nombre de una variable.
// @SerealizeName("nombreAtributoExactoServidor")
// por lo que al declara la variable con este decorador podemos nombrarla de la forma que queramos.

// si es -1 no encontro el id del server.
// listOf() inicializa una lista vacia.
data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1,
    val movie_type: String = ""
)

//ROOM
// almacenamos una tabla en la base de datos. SQlite
// Dos objetos en la base local y el otro para el parser de la api.
// Dentro de SQL se creara esta tabla con las columnas que se van a utilizar.
// @Entity crea la tabla.
// @PrimaryKey indica que es la llave primaria.
// @ColumnInfo indica el nombre de la columna. en este caso son 12 columnas para ingresar datos en la tabla.
@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,
    @ColumnInfo(name = "movie_type")
    val movie_type: String = ""
)

// resultados contiene la lista de peliculas.
data class MovieList(val results: List<Movie> = listOf())

// Esta funcion transforma la lista MovieEntity. a MovieList.
fun List<MovieEntity>.toMovieList(): MovieList {
    val resultList = mutableListOf<Movie>()
    this.forEach { movieEntity ->
        resultList.add(movieEntity.toMovie())
    }
    return MovieList(resultList)
}


fun MovieEntity.toMovie(): Movie = Movie(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_language,
    this.original_title,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    this.movie_type
)

fun Movie.toMovieEntity(movieTye: String): MovieEntity = MovieEntity(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_language,
    this.original_title,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    movie_type = movieTye
)