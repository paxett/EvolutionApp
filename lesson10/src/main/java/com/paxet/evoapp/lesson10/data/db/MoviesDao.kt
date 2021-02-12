package com.paxet.evoapp.lesson10.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movies>)

    @Insert
    fun insertMovie(movie: Movies): Long

    @Query("DELETE FROM movies WHERE id = :movieId")
    fun deleteMovie(movieId: Long)

    @Query("DELETE FROM movies")
    fun deleteAll()
}