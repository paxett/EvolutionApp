package com.paxet.evoapp.lesson4

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesList : Fragment(R.layout.fragment_movies_list) {

    val movies : List<Movie> by lazy {
        DataUtils().generateMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.rv_movies_list).run {
            adapter = MoviesAdapter(movies, view.findNavController())
            layoutManager = GridLayoutManager(view.context, 2)
        }
    }
}