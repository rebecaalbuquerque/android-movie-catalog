package com.albuquerque.moviecatalog.app.view.holder

import androidx.databinding.ViewDataBinding
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.core.adapter.BaseAdapter
import com.albuquerque.moviecatalog.databinding.ItemMovieBinding

class MovieViewHolder(binding: ViewDataBinding): BaseAdapter.BaseViewHolder<MovieUI>(binding) {

    override fun bind(item: MovieUI) {
        with(binding as ItemMovieBinding) {
            movie = item
        }
    }
}