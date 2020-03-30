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

    constructor(list: List<MovieUI>, isGrid: Boolean = false) : this() {
        this.isGrid = isGrid
        refresh(list)
    }

    constructor(isGrid: Boolean = false) : this() {
        this.isGrid = isGrid
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = if(isGrid)
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_grid, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(ItemMovieBindingImpl(null, view) as ItemMovieBinding)
    }

}