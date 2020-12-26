package com.paxet.evoapp.lesson6

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paxet.evoapp.lesson6.data.Movie
import com.paxet.evoapp.lesson6.data.loadMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesList : Fragment(R.layout.fragment_movies_list) {
    private val scope = CoroutineScope(Dispatchers.IO)
    var movies : List<Movie> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        scope.launch {
            movies = loadMovies(requireContext())
            view.findViewById<RecyclerView>(R.id.rv_movies_list).run {
                withContext(Dispatchers.Main) {
                    adapter = MoviesAdapter(movies, view.findNavController())
                    layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }
        }


    }
}