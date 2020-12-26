package com.paxet.evoapp.lesson6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.paxet.evoapp.lesson6.data.Movie

class MoviesAdapter(
        var movies : List<Movie>,
        val navController : NavController
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int =  movies.size
    //fun getItem(position: Int): Movie = movies.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("movie", movies[position])
            navController.navigate(R.id.action_moviesList_to_movieDetails, bundle)
        }
    }
}