package com.sample.moviesearchengine.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.moviesearchengine.R
import com.sample.moviesearchengine.model.Movie
import kotlinx.android.synthetic.main.custom_movie.view.*

class MovieAdapter(private val listener: Listener) : RecyclerView.Adapter<MovieAdapter.TasksViewHolder>() {
    private val items = ArrayList<Movie>()

    fun setItems(items: ArrayList<Movie>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): TasksViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_movie, parent, false)
        return TasksViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: TasksViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.set(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TasksViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        @SuppressLint("SetTextI18n")
        fun set(item: Movie?) {
            itemView.textName.text = item?.title
            itemView.textDate.text = item?.release_date

            itemView.setOnClickListener { listener.onItemClick(item!!) }
        }
    }

    interface Listener {
        fun onItemClick(movie: Movie)
    }

}