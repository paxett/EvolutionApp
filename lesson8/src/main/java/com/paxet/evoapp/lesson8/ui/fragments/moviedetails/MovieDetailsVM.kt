package com.paxet.evoapp.lesson8.ui.fragments.moviedetails

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paxet.evoapp.lesson8.data.network.tmdbapi.MovieCreditsAPI
import com.paxet.evoapp.lesson8.data.network.tmdbapi.MovieDetailsAPI
import com.paxet.evoapp.lesson8.ui.fragments.BaseVM
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MovieDetailsVM(app: Application) : BaseVM(app) {
    private val _movieLD = MutableLiveData<Pair<MovieDetailsAPI, MovieCreditsAPI>>()
    val movieLD : LiveData<Pair<MovieDetailsAPI, MovieCreditsAPI>> get() = _movieLD

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
    }

    fun initMovie(arguments: Bundle?) {
        val movieId = arguments?.get("movieId").toString()
        coroutineScope.launch(exceptionHandler) {
            val movieDetails = tmdbAPI.getMovieDetails(movieId, apiKey)
            val movieCredits = tmdbAPI.getMovieCredits(movieId, apiKey)
            _movieLD.postValue(Pair(movieDetails, movieCredits))
        }
    }

    companion object {
        private val TAG = MovieDetailsVM::class.java.simpleName
    }
}