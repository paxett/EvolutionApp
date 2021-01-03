package com.paxet.evoapp.lesson7.ui.fragments.moviedetails

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paxet.evoapp.lesson7.data.Movie

class MovieDetailsVM : ViewModel() {
    private val _movieLD = MutableLiveData<Movie>()
    val movieLD : LiveData<Movie> get() = _movieLD

    fun initMovie(arguments: Bundle?) {
        _movieLD.postValue(arguments?.getParcelable("movie"))
    }
}