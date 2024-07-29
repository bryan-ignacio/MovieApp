package com.demoapp.carteleragt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demoapp.carteleragt.data.model.Movie
import com.demoapp.carteleragt.data.model.MovieEntity

@Dao
interface MovieDao {
    // obtener informacion de la base de datos.
    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    //Insertar informacion en la base de datos.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Movie)

}