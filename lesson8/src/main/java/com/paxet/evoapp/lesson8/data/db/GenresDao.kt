package com.paxet.evoapp.lesson8.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenresDao {
    @Query("SELECT * FROM genres")
    fun getAll(): List<Genres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(genres: List<Genres?>)

    @Insert
    fun insertGenre(genre: Genres): Long

    @Query("DELETE FROM genres WHERE id = :genreId")
    fun deleteGenre(genreId: Long)

    @Query("DELETE FROM genres")
    fun deleteAll()
}