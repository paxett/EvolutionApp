package com.example.lesson3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class MoviesList : Fragment(R.layout.fragment_movies_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.bg).apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_moviesList_to_movieDetails)
//      Use NavController instead of Fragment Manager
//                parentFragmentManager.beginTransaction()
//                    .addToBackStack(null)
//                    .replace(R.id.parent_fragment, MovieDetails())
//                    .commit()
            }
        }
    }
}