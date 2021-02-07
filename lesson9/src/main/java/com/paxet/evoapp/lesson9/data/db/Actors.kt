package com.paxet.evoapp.lesson9.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paxet.evoapp.lesson9.data.network.tmdbapi.CastItem

@Entity(tableName = "actors")
data class Actors (
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int? = null,
    @ColumnInfo(name="actor_name")
    val name: String?,
    @ColumnInfo(name="movie_id")
    val movieId: String,
    @ColumnInfo(name="profile_pPath")
    val profilePath: String?
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
    profilePath,
    id,
    null,
    null
    )
}