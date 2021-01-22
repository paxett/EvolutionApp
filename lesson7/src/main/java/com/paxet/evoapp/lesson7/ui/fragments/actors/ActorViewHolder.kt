package com.paxet.evoapp.lesson7.ui.fragments.actors

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paxet.evoapp.lesson7.R
import com.paxet.evoapp.lesson7.data.network.NetworkModule
import com.paxet.evoapp.lesson7.data.tmdbapi.CastItem

class ActorViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val avatar : ImageView = itemView.findViewById(R.id.avatar)
    private val actor_name : TextView = itemView.findViewById(R.id.actor_name)
    private val view = view

    fun bind(actor : CastItem?) {
        Glide.with(view)
            .load("${NetworkModule.baseImageUrl}/w92/${actor?.profilePath}").into(avatar)

        actor_name.text = actor?.name ?: ""
    }
}