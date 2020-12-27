package com.paxet.evoapp.lesson6.ui.fragments.moviedetails

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paxet.evoapp.lesson6.data.Movie

class MovieDetailsVM : ViewModel() {
    val movieLD = MutableLiveData<Movie>()
    fun initMovie(arguments: Bundle?) {
        movieLD.postValue(arguments?.getParcelable("movie"))
    }
}