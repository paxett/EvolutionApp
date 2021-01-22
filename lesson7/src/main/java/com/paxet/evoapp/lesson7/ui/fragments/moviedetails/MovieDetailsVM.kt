package com.paxet.evoapp.lesson7.ui.fragments.moviedetails

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paxet.evoapp.lesson7.data.network.NetworkModule.tmdbAPI
import com.paxet.evoapp.lesson7.data.tmdbapi.MovieCreditsAPI
import com.paxet.evoapp.lesson7.data.tmdbapi.MovieDetailsAPI
import com.paxet.evoapp.lesson7.ui.fragments.BaseVM
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MovieDetailsVM : BaseVM() {
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