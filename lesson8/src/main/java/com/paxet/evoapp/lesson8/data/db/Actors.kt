package com.paxet.evoapp.lesson8.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paxet.evoapp.lesson8.data.network.tmdbapi.CastItem

@Entity(tableName = "actors")
data class Actors (
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int? = null,
    val name: String?,
    val movieId: String
)

fun Actors.toCastItem() : CastItem {
    return CastItem(null,
    null,
    null,
    null,
    null,
    null,
    null,
    name,
    null,
    id,
    null,
    null
    )
}