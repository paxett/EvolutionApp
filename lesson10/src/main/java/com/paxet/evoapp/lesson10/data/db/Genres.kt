package com.paxet.evoapp.lesson10.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paxet.evoapp.lesson10.data.network.tmdbapi.GenresItem

@Entity(tableName="genres")
data class Genres (
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int? = null,

    @ColumnInfo(name="name")
    val name: String? = null
)

fun Genres.toGenresItem() : GenresItem {
    return GenresItem(
        this.name,
        this.id
    )
}