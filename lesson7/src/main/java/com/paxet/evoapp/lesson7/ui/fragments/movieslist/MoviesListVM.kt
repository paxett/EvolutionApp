package com.paxet.evoapp.lesson7.ui.fragments.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paxet.evoapp.lesson7.data.network.NetworkModule
import com.paxet.evoapp.lesson7.data.tmdbapi.MovieItemAPI
import com.paxet.evoapp.lesson7.ui.fragments.BaseVM
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.*

class MoviesListVM : BaseVM() {

    private val _moviesListLD = MutableLiveData<List<MovieItemAPI>>()
    val moviesListLD : LiveData<List<MovieItemAPI>> get() = _moviesListLD

    val tmdbAPI = NetworkModule.tmdbAPI

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
    }

    fun initMoviesList() {
        coroutineScope.launch(exceptionHandler) {
            val moviesNowPlaying = tmdbAPI.getNowPlaying(apiKey).results ?: listOf()
            _moviesListLD.postValue(moviesNowPlaying as List<MovieItemAPI>?)
        }
    }

    fun searchMoviesList(query : String) {
        coroutineScope.launch(exceptionHandler) {
            val moviesNowPlaying = tmdbAPI.searchMovies(query, apiKey).results ?: listOf()
            _moviesListLD.postValue(moviesNowPlaying as List<MovieItemAPI>?)
        }
    }

    var timer = Timer()
    val DELAY: Long = 1000L
    fun initTimer(searchLine : String) {
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
        }, DELAY)
    }

    companion object {
        private val TAG = MoviesListVM::class.java.simpleName
    }

}