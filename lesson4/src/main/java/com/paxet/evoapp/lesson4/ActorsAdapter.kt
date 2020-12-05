package com.paxet.evoapp.lesson4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter(
    context : Context,
    var actors : List<Actor>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val inflater : LayoutInflater = LayoutInflater.from(context)

        override fun getItemCount(): Int = actors.size
        fun getItem(position : Int) : Actor = actors.get(position)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }
}