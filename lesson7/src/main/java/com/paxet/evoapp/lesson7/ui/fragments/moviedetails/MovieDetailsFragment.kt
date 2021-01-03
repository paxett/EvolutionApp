package com.paxet.evoapp.lesson7.ui.fragments.moviedetails

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paxet.evoapp.lesson7.R

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    val viewModel : MovieDetailsVM by viewModels()
    val actorsAdapter = ActorsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObservers(view)

        view.findViewById<RecyclerView>(R.id.rv_actors_list).run {
            adapter = actorsAdapter
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }
        viewModel.initMovie(arguments)
    }

    private fun initObservers(view : View) {
        val bw_poster : ImageView = view.findViewById(R.id.bw_poster)
        val age : TextView = view.findViewById(R.id.age)
        val name : TextView = view.findViewById(R.id.name)
        val genresTV : TextView = view.findViewById(R.id.genres)
        val rating : RatingBar = view.findViewById(R.id.ratingBar)
        val reviewCounter : TextView = view.findViewById(R.id.counter)
        val storyLine: TextView = view.findViewById(R.id.body)

        viewModel.movieLD.observe(viewLifecycleOwner, Observer { movie ->
            movie?.run {
                Glide.with(view).load(backdrop).into(bw_poster)
                age.text = minimumAge.toString() + "+"
                name.text = title
                genresTV.text = genres.joinToString { it.name }
                rating.rating = ratings ?: 0f
                reviewCounter.text = numberOfRatings.toString()
                storyLine.text = overview

                actorsAdapter.actors = actors
            }
        })
    }
}