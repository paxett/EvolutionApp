package com.paxet.evoapp.lesson8.ui.fragments.actors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paxet.evoapp.lesson8.R
import com.paxet.evoapp.lesson8.data.tmdbapi.CastItem

class ActorsAdapter(
) : RecyclerView.Adapter<ActorViewHolder>() {
        var actors : List<CastItem?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
        override fun getItemCount(): Int = actors.size
        fun getItem(position : Int) : CastItem? = actors.get(position)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ActorViewHolder(inflater.inflate(R.layout.view_holder_actor, parent, false))
        }

        override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
            holder.bind(getItem(position))
        }
}