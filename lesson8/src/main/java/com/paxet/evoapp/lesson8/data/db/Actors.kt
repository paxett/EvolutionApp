package com.paxet.evoapp.lesson8.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "actors",
    foreignKeys = [ForeignKey(
        entity = Movies::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("filmId"),
        onDelete = CASCADE
)])
data class Actors (
    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int? = null,
    val name: String,
    val filmId: String
)