package com.paxet.evoapp.lesson10.ui.fragments.actors

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.paxet.evoapp.lesson10.R
import com.paxet.evoapp.lesson10.data.network.NetworkModule
import com.paxet.evoapp.lesson10.data.network.tmdbapi.CastItem

class ActorViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val avatar : ImageView = itemView.findViewById(R.id.avatar)
    private val actor_name : TextView = itemView.findViewById(R.id.actor_name)
    private val view = view

    fun bind(actor : CastItem?) {
        if(!actor?.profilePath.isNullOrEmpty()) {
            Glide.with(view)
                .load("${NetworkModule.baseImageUrl}/w92/${actor?.profilePath}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(avatar)
        }

        actor_name.text = actor?.name ?: ""
    }
}