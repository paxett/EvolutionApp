package com.paxet.evoapp.lesson10.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movies::class, Actors::class, Genres::class], version = 5)
//TODO add TypeConverters

abstract class AppDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
    abstract val genresDao: GenresDao
    abstract val actorsDao: ActorsDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDBInstance(applicationContext: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "MoviesAndActors.db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return  instance
            }
        }
    }
}