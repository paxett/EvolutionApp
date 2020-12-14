package com.paxet.evoapp.lesson5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(
    var movies : List<Movie>,
    val navController : NavController
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int = movies.size
    fun getItem(position: Int): Movie = movies.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.like_disabled.setOnClickListener {
            //like movie
            movies.get(position).like = true
            this.notifyItemChanged(position)
        }
        holder.like_enabled.setOnClickListener {
            //dislike movie
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