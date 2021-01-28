package com.paxet.evoapp.lesson8.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paxet.evoapp.lesson8.data.network.tmdbapi.GenresItem

@Entity(tableName="genres")
data class Genres (
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int? = null,

    @ColumnInfo(name="name")
    val name: String? = null
) {
    constructor(genresItem: GenresItem?) :
            this(genresItem?.id, genresItem?.name)
}