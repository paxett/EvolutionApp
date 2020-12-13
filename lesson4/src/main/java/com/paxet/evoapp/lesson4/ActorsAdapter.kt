package com.paxet.evoapp.lesson4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter(
    //context : Context,
    var actors : List<Actor>
    ) : RecyclerView.Adapter<ActorViewHolder>() {
        override fun getItemCount(): Int = actors.size
        fun getItem(position : Int) : Actor = actors.get(position)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ActorViewHolder(inflater.inflate(R.layout.view_holder_actor, parent, false))
        }

        override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
            holder.bind(getItem(position))
        }
}