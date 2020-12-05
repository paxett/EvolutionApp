package com.paxet.evoapp.lesson4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(
    context : Context?,
    var movies : List<Movie>
) : RecyclerView.Adapter<MovieViewHolder>() {
    private val inflater : LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = movies.size
    fun getItem(position : Int) : Movie = movies.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}