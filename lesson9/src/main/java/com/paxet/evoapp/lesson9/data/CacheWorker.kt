package com.paxet.evoapp.lesson9.data

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.paxet.evoapp.lesson9.data.db.AppDatabase
import com.paxet.evoapp.lesson9.data.network.NetworkModule.tmdbAPI
import com.paxet.evoapp.lesson9.data.network.tmdbapi.MovieItemAPI
import com.paxet.evoapp.lesson9.data.network.tmdbapi.toMovies
import com.paxet.evoapp.lesson9.ui.fragments.BaseVM
import kotlinx.coroutines.*

class CacheWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        // Indicate whether the work finished successfully with the Result
        Log.e("CacheWorker.doWork()", "Doing work..")
        updateMoviesCache()
        Log.e("CacheWorker.doWork()", "Finished work..")
        return Result.success()
    }

    val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    val db by lazy {
        AppDatabase.getDBInstance(appContext)
    }

    private fun updateMoviesCache() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                //Get movies from network
                try {
                    val remoteMovies: List<MovieItemAPI> =
                        tmdbAPI.getNowPlaying(BaseVM.apiKey).results ?: listOf()
                    if (remoteMovies.isNotEmpty()) {
                        //Store movies to DB cache
                        db.moviesDao.insertAll(remoteMovies.map{ it.toMovies() })
                    }
                } catch (e: Exception) {
                    print(e.message)
                }
            }
        }
    }
}