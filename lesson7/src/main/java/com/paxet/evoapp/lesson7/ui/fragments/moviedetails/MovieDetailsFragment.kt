package com.paxet.evoapp.lesson7.ui.fragments.moviedetails

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
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
import com.paxet.evoapp.lesson7.data.network.NetworkModule
import com.paxet.evoapp.lesson7.ui.fragments.actors.ActorsAdapter

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

    private fun initObservers(view: View) {
        val bw_poster : ImageView = view.findViewById(R.id.bw_poster)
        val age : TextView = view.findViewById(R.id.age)
        val name : TextView = view.findViewById(R.id.name)
        val genresTV : TextView = view.findViewById(R.id.genres)
        val rating : RatingBar = view.findViewById(R.id.ratingBar)
        val reviewCounter : TextView = view.findViewById(R.id.counter)
        val storyLine: TextView = view.findViewById(R.id.body)

        viewModel.movieLD.observe(viewLifecycleOwner, Observer { movie ->
            movie?.first.run {
                Glide.with(view)
                        .load("${NetworkModule.baseImageUrl}/w342/${this?.backdropPath}")
                        .into(bw_poster)

                val colorMatrix = ColorMatrix()
                colorMatrix.setSaturation(0f)
                val filter = ColorMatrixColorFilter(colorMatrix)
                bw_poster.setColorFilter(filter)

                age.text = if (this?.adult == true) "18+" else "0+"
                name.text = this?.title ?: ""
                genresTV.text = this?.genres?.joinToString { it?.name.toString() } ?: ""
                rating.rating = this?.voteAverage?.toFloat() ?: 0f
                reviewCounter.text = this?.voteCount.toString()
                storyLine.text = this?.overview ?: ""
            }

            movie?.second.run {
                actorsAdapter.actors = this?.cast ?: listOf()
            }
        })
    }
}