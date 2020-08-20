package com.albuquerque.moviecatalog.app.view.holder

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.albuquerque.moviecatalog.app.adapter.MoviesAdapter
import com.albuquerque.moviecatalog.app.adapter.MoviesAdapter.TypeMovieView.*
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.core.adapter.BaseAdapter
import com.albuquerque.moviecatalog.databinding.ItemMovieBinding
import com.albuquerque.moviecatalog.databinding.ItemMovieGridBinding
import com.albuquerque.moviecatalog.databinding.ItemMovieLinearBinding

class MovieViewHolder(binding: ViewDataBinding): BaseAdapter.BaseViewHolder<MovieUI>(binding) {

    private lateinit var typeMovieView: MoviesAdapter.TypeMovieView
    private lateinit var onItemClicked: (movie: MovieUI) -> Unit

    override fun bind(item: MovieUI) {

        when(typeMovieView) {
            DEFAULT -> {
                with(binding as ItemMovieBinding) {
                    movie = item

                    root.setOnClickListener { onItemClicked(item) }

                    root.setOnLongClickListener {
                        Toast.makeText(this.root.context, item.title, Toast.LENGTH_LONG).show()
                        true
                    }
                }
            }

            GRID -> {
                with(binding as ItemMovieGridBinding) {
                    movie = item

                    root.setOnClickListener { onItemClicked(item) }

                    root.setOnLongClickListener {
                        Toast.makeText(this.root.context, item.title, Toast.LENGTH_LONG).show()
                        true
                    }
                }
            }

            LINEAR -> {
                with(binding as ItemMovieLinearBinding) {
                    movie = item
                }
            }
        }

    }

    fun bind(item: MovieUI, typeMovieView: MoviesAdapter.TypeMovieView, onItemClicked: (movie: MovieUI) -> Unit) {
        this.typeMovieView = typeMovieView
        this.onItemClicked = onItemClicked
        bind(item)
    }

}