package com.paxet.evoapp.lesson4

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MovieDetails : Fragment(R.layout.fragment_movie_details) {
    private var rv_actors_list: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_actors_list = view.findViewById(R.id.rv_actors_list)
        val actors = DataUtils().generateActors()
        val actors_adapter = ActorsAdapter(view.context, actors)
        rv_actors_list?.adapter = actors_adapter
        rv_actors_list?.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    }
}