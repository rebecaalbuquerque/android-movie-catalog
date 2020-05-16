package com.albuquerque.moviecatalog.app.view.holder

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.core.adapter.BaseAdapter
import com.albuquerque.moviecatalog.databinding.ItemMovieBinding

class MovieViewHolder(binding: ViewDataBinding): BaseAdapter.BaseViewHolder<MovieUI>(binding) {

    private lateinit var onItemClicked: (movie: MovieUI) -> Unit

    override fun bind(item: MovieUI) {
        with(binding as ItemMovieBinding) {
            movie = item

            root.setOnClickListener {
                onItemClicked(item)
            }

            root.setOnLongClickListener {
                Toast.makeText(this.root.context, item.title, Toast.LENGTH_LONG).show()
                true
            }
        }

    }

    fun bind(item: MovieUI, onItemClicked: (movie: MovieUI) -> Unit) {
        this.onItemClicked = onItemClicked
        bind(item)
    }

}