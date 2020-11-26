package com.example.lesson3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class MoviesList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view.findViewById<ImageView>(R.id.bg).apply {
            setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.parent_fragment, MovieDetails())
                    .commit()
            }
        }
        return view
    }
}