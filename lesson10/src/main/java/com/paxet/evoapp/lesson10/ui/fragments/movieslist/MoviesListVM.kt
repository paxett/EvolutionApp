package com.paxet.evoapp.lesson10.ui.fragments.movieslist

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.paxet.evoapp.lesson10.data.db.toMoviesApi
import com.paxet.evoapp.lesson10.data.network.NetworkModule.tmdbAPI
import com.paxet.evoapp.lesson10.data.network.tmdbapi.MovieItemAPI
import com.paxet.evoapp.lesson10.data.network.tmdbapi.toMovies
import com.paxet.evoapp.lesson10.ui.fragments.BaseVM
import com.paxet.evoapp.lesson10.ui.notifications.NewMovieNotification
import com.paxet.evoapp.lesson10.ui.notifications.Notification
import kotlinx.coroutines.*
import java.util.*

class MoviesListVM(app: Application) : BaseVM(app) {

    private val app: Application = app

    private val _moviesListLD = MutableLiveData<List<MovieItemAPI>>()
    val moviesListLD : LiveData<List<MovieItemAPI>> get() = _moviesListLD

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
    }

    fun initMoviesList() {
        Log.e(TAG, "#initMoviesList#")
        val notification: Notification = NewMovieNotification(app)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                initConfiguration()
                //Get genres from network or DB cache
                initGenres()

                //Get movies from DB cache
                val localMovies: List<MovieItemAPI> = readMoviesFromDb()
                if(localMovies.isNotEmpty()) {
                    _moviesListLD.postValue(localMovies)
                }

                //Get movies from network
                var remoteMovies: List<MovieItemAPI> = listOf()
                try {
                    remoteMovies = tmdbAPI.getNowPlaying(apiKey).results ?: listOf()
                    if(remoteMovies.isNotEmpty()) {
                        _moviesListLD.postValue(remoteMovies)
                        //Store movies to DB cache
                        writeMoviesToDb(remoteMovies)
                    }
                } catch (e: Exception) {
                    print(e.message)
                }
                val bestNewMovie = remoteMovies.minus(localMovies).maxByOrNull { it -> it.voteAverage ?: 0.0 }
                if (bestNewMovie != null) {
                    notification.showNotification(bestNewMovie)
                } else {
                    //Dummy notification just for tests
                    //TODO: remove this
                    notification.showNotification(remoteMovies.get(0))
                    Log.e(TAG, "Dummy notification just for tests")
                }
            }
        }
    }

    fun readMoviesFromDb() : List<MovieItemAPI> {
        return db.moviesDao.getAll().map{ it.toMoviesApi() }
    }

    fun writeMoviesToDb(remoteMovies: List<MovieItemAPI>) {
        db.moviesDao.insertAll(remoteMovies.map{ it.toMovies() })
    }

    fun searchMoviesList(query: String) {
        coroutineScope.launch(exceptionHandler) {
            val moviesNowPlaying = tmdbAPI.searchMovies(query, apiKey).results ?: listOf()
            _moviesListLD.postValue(moviesNowPlaying as List<MovieItemAPI>?)
        }
    }

    var timer = Timer()
    fun initTimer(searchLine: String, delay: Long) {
        timer.cancel()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                if(searchLine == "") {
                    initMoviesList()
                } else {
                    searchMoviesList(searchLine)
                }
            }
        }, delay)
    }

    companion object {
        private val TAG = MoviesListVM::class.java.simpleName
    }

}