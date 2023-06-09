package com.example.geekjokesproject.ui.dashboard.geekjokes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geekjokesproject.databinding.GeekJokesItemBinding
import com.example.geekjokesproject.db.Jokes

class GeekJokesAdapter() : RecyclerView.Adapter<GeekJokesAdapter.ViewHolder>() {
    lateinit var context: Context
    lateinit var list: MutableList<Jokes>

    constructor(context: Context) : this() {
        this.context = context
        list = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GeekJokesItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, context)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jokes = list[position]
        holder.bind(jokes)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    public fun addJokes(jokes: List<Jokes>) {
        list.clear()
        list.addAll(jokes)
        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    class ViewHolder(val binding: GeekJokesItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Jokes) {
            item.jokes?.let {
                binding.tvJokes.text = it
            }
        }
    }
}