package com.paxet.evoapp.lesson5

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MovieDetails : Fragment(R.layout.fragment_movie_details) {

    val actors : List<Actor> by lazy {
        DataUtils().generateActors()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.rv_actors_list).run {
            adapter = ActorsAdapter(actors)
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}