package com.paxet.evoapp.lesson4

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val avatar = itemView.findViewById<ImageView>(R.id.avatar)
    private val actor_name = itemView.findViewById<TextView>(R.id.actor_name)

    fun bind(actor : Actor) {
        avatar.setImageResource(actor.avatar)
        actor_name.text = actor.name
    }
}