package com.paxet.evoapp.lesson7.ui.fragments.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.paxet.evoapp.lesson7.R
import com.paxet.evoapp.lesson7.data.tmdbapi.MovieItemAPI

class MoviesListAdapter(
    view : View
) : RecyclerView.Adapter<MovieViewHolder>() {
    val view = view
    var movies : List<MovieItemAPI> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =  movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("movieId", movies[position].id ?: -1)
            view.findNavController().navigate(R.id.action_moviesList_to_movieDetails, bundle)
        }
    }
}