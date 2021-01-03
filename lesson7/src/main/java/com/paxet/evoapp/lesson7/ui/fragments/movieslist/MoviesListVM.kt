package com.paxet.evoapp.lesson7.ui.fragments.movieslist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paxet.evoapp.lesson7.data.Movie
import com.paxet.evoapp.lesson7.data.loadMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListVM : ViewModel() {
    private val _moviesListLD = MutableLiveData<List<Movie>>()
    val moviesListLD : LiveData<List<Movie>> get() = _moviesListLD
    private val scope = CoroutineScope(Dispatchers.IO)

    fun initMoviesList(view : View) {
        scope.launch {
            var movies = loadMovies(view.context)
            _moviesListLD.postValue(movies)
        }
    }
}