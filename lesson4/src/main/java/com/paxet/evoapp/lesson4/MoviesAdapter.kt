package com.paxet.evoapp.lesson4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(
    context : Context,
    var movies : List<Movie>,
    val navController : NavController
) : RecyclerView.Adapter<MovieViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = movies.size
    fun getItem(position: Int): Movie = movies.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder =  MovieViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
        viewHolder.like_enabled
        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.like_disabled.setOnClickListener {
            movies.get(position).like = true
            this.notifyItemChanged(position)
        }
        holder.like_enabled.setOnClickListener {
            movies.get(position).like = false
            this.notifyItemChanged(position)
        }
        if(position == 0) {
            holder.itemView.setOnClickListener {
                navController.navigate(R.id.action_moviesList_to_movieDetails)
            }
        }
    }
}