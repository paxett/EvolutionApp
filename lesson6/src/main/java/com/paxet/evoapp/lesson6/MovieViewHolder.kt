package com.paxet.evoapp.lesson6

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paxet.evoapp.lesson6.data.Movie

class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val poster : ImageView = itemView.findViewById(R.id.color_poster)
    private val like_enabled : ImageView = itemView.findViewById(R.id.like_enabled)
    private val like_disabled : ImageView = itemView.findViewById(R.id.like_disabled)
    private val title : TextView = itemView.findViewById(R.id.movie_name)
    private val genres : TextView = itemView.findViewById(R.id.genres)
    private val reviews_counter : TextView = itemView.findViewById(R.id.reviews_counter)
    private val age : TextView = itemView.findViewById(R.id.age)
    private val duration : TextView = itemView.findViewById(R.id.duration)
    private val ratingBar : RatingBar = itemView.findViewById(R.id.ratingBar)
    private val view = view

    fun bind(movie : Movie) {
        Glide.with(view).load(movie.poster).into(poster)
        title.text = movie.title
        genres.text = movie.genres.joinToString { it.name }
        reviews_counter.text = movie.numberOfRatings.toString()
        age.text = movie.minimumAge.toString() + "+"
        ratingBar.rating = movie.ratings
        duration.text = movie.runtime.toString()
    }
}