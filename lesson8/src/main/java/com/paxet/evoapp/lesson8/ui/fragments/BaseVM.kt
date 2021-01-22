package com.paxet.evoapp.lesson8.ui.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import com.paxet.evoapp.lesson8.data.GenresData
import com.paxet.evoapp.lesson8.data.network.NetworkModule
import com.paxet.evoapp.lesson8.data.network.NetworkModule.tmdbAPI
import kotlinx.coroutines.*

abstract class BaseVM : ViewModel() {
    val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(BaseVM.TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
    }

    fun initConfiguration() {
        coroutineScope.launch(exceptionHandler) {
            NetworkModule.baseImageUrl = tmdbAPI.getAPIConfiguration(apiKey).images?.secureBaseUrl ?: ""
        }
    }

    fun initGenres() {
        coroutineScope.launch(exceptionHandler) {
            GenresData.genresData = tmdbAPI.getGenres(apiKey)
        }
    }

    companion object {
        private val TAG = BaseVM::class.java.simpleName
        val apiKey = "c9e69769a0b528e00cf6da3c3199eb0e"
    }
}