package com.paxet.evoapp.lesson5

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val poster : ImageView = itemView.findViewById(R.id.color_poster)
    val like_enabled : ImageView = itemView.findViewById(R.id.like_enabled)
    val like_disabled : ImageView = itemView.findViewById(R.id.like_disabled)
    val name : TextView = itemView.findViewById(R.id.movie_name)
    private val genres : TextView = itemView.findViewById(R.id.genres)
    private val reviews_counter : TextView = itemView.findViewById(R.id.reviews_counter)
    private val age : TextView = itemView.findViewById(R.id.age)
    private val duration : TextView = itemView.findViewById(R.id.duration)
    private val ratingBar : RatingBar = itemView.findViewById(R.id.ratingBar)

    fun bind(movie : Movie) {
        poster.setImageResource(movie.poster)
        name.text = movie.name
        genres.text = movie.genres
        reviews_counter.text = movie.reviews_counter
        age.text = movie.age
        duration.text = movie.duration
        ratingBar.rating = movie.rating
        if (movie.like) {
            like_enabled.visibility = View.VISIBLE
            like_disabled.visibility = View.INVISIBLE
        } else {
            like_enabled.visibility = View.INVISIBLE
            like_disabled.visibility = View.VISIBLE
        }
    }
}