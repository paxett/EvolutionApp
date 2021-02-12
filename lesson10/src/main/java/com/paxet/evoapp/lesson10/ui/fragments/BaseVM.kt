package com.paxet.evoapp.lesson10.ui.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.work.*
import com.paxet.evoapp.lesson10.data.CacheWorker
import com.paxet.evoapp.lesson10.data.db.AppDatabase
import com.paxet.evoapp.lesson10.data.db.toGenresItem
import com.paxet.evoapp.lesson10.data.network.GenresData
import com.paxet.evoapp.lesson10.data.network.NetworkModule
import com.paxet.evoapp.lesson10.data.network.NetworkModule.tmdbAPI
import com.paxet.evoapp.lesson10.data.network.tmdbapi.GenresAPI
import com.paxet.evoapp.lesson10.data.network.tmdbapi.toGenres
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

abstract class BaseVM(app: Application) : AndroidViewModel(app) {
    init {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val cacheWorkRequest = PeriodicWorkRequestBuilder<CacheWorker>(8, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(app)
        workManager.enqueueUniquePeriodicWork("DB cache updater", ExistingPeriodicWorkPolicy.KEEP, cacheWorkRequest)
    }

    val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception, scope active:${throwable.localizedMessage}", throwable)
    }

    val db by lazy {
        AppDatabase.getDBInstance(app)
    }

    fun initConfiguration() {
        coroutineScope.launch(exceptionHandler) {
            NetworkModule.baseImageUrl = tmdbAPI.getAPIConfiguration(apiKey).images?.secureBaseUrl ?: ""
        }
    }

    fun initGenres() {
        coroutineScope.launch(exceptionHandler) {
            GenresData.genresData = GenresAPI( db.genresDao.getAll().map { it.toGenresItem() } )
            val genresData = tmdbAPI.getGenres(apiKey)
            if (genresData.genres != null) {
                //Store genres to DB cache
                GenresData.genresData = genresData
                db.genresDao.insertAll(genresData.genres.map { it?.toGenres()})
            }
        }
    }

    companion object {
        private val TAG = BaseVM::class.java.simpleName
        val apiKey = "c9e69769a0b528e00cf6da3c3199eb0e"
    }
}