package com.albuquerque.moviecatalog.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.view.holder.MovieViewHolder
import com.albuquerque.moviecatalog.core.adapter.BaseAdapter
import com.albuquerque.moviecatalog.databinding.ItemMovieBinding
import com.albuquerque.moviecatalog.databinding.ItemMovieBindingImpl

class MoviesAdapter(): BaseAdapter<MovieUI, MovieViewHolder>() {

    private var isGrid: Boolean = false
    private lateinit var onItemClicked: (movie: MovieUI) -> Unit

    constructor(list: List<MovieUI>, onItemClicked: (movie: MovieUI) -> Unit, isGrid: Boolean = false) : this() {
        this.isGrid = isGrid
        this.onItemClicked = onItemClicked
        refresh(list)
    }

    constructor(isGrid: Boolean = false, onItemClicked: (movie: MovieUI) -> Unit) : this() {
        this.onItemClicked = onItemClicked
        this.isGrid = isGrid
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = if(isGrid)
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_grid, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(ItemMovieBindingImpl(null, view) as ItemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItemAt(position), onItemClicked)
    }

}