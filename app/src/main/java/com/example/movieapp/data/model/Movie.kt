package com.example.movieapp.data.model

// Con retrofit podemos utilizar una funcion llamada serealizeName para cambiar el nombre de una variable.
// @SerealizeName("nombreAtributoExactoServidor")
// por lo que al declara la variable con este decorador podemos nombrarla de la forma que queramos.

// si es -1 no encontro el id del server.
// listOf() inicializa una lista vacia.
data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    val genre_ids: List<Int> = listOf(),
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1
) {

}

// resultados contiene la lista de peliculas.
data class MovieList(val results:List<Movie> = listOf()){

}
