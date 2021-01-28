package com.paxet.evoapp.lesson8.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ActorsDao {
    @Query("SELECT * FROM actors")
    fun getAll(): List<Actors>
}