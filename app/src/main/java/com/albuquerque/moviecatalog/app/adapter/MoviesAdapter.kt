package com.albuquerque.moviecatalog.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.adapter.MoviesAdapter.TypeMovieView.*
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.view.holder.MovieViewHolder
import com.albuquerque.moviecatalog.core.adapter.BaseAdapter
import com.albuquerque.moviecatalog.databinding.*

class MoviesAdapter(): BaseAdapter<MovieUI, MovieViewHolder>() {

    private var typeMovieView: TypeMovieView = DEFAULT
    private lateinit var onItemClicked: (movie: MovieUI) -> Unit

    constructor(typeMovieView: TypeMovieView = DEFAULT, onItemClicked: (movie: MovieUI) -> Unit) : this() {
        this.typeMovieView = typeMovieView
        this.onItemClicked = onItemClicked
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return when(typeMovieView) {
            DEFAULT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(ItemMovieBindingImpl(null, view) as ItemMovieBinding)
            }

            GRID -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_grid, parent, false)
                MovieViewHolder(ItemMovieGridBindingImpl(null, view) as ItemMovieGridBinding)
            }

            LINEAR -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_linear, parent, false)
                MovieViewHolder(ItemMovieLinearBindingImpl(null, view) as ItemMovieLinearBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItemAt(position), typeMovieView, onItemClicked)
    }

    enum class TypeMovieView {
        DEFAULT, GRID, LINEAR;
    }

}