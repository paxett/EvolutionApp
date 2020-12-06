package com.paxet.evoapp.lesson4

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesList : Fragment(R.layout.fragment_movies_list) {

    private var rv_movies_list: RecyclerView? = null
    val movies = DataUtils().generateMovies()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
        rv_movies_list  = view.findViewById(R.id.rv_movies_list)
        val movie_adapter = MoviesAdapter(view.context, movies, view.findNavController())
        rv_movies_list?.adapter = movie_adapter
        rv_movies_list?.layoutManager = GridLayoutManager(view.context, 2)

//        view.findViewById<ImageView>(R.id.bg).apply {
//            setOnClickListener {
//                findNavController().navigate(R.id.action_moviesList_to_movieDetails)
//      Use NavController instead of Fragment Manager
//                parentFragmentManager.beginTransaction()
//                    .addToBackStack(null)
//                    .replace(R.id.parent_fragment, MovieDetails())
//                    .commit()
            }
}