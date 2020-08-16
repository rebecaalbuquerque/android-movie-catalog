package com.albuquerque.moviecatalog.app.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.viewmodel.MovieDetailViewModel
import com.albuquerque.moviecatalog.databinding.ActivityMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "MOVIE"
    }

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private lateinit var binding: ActivityMovieDetailBinding

    private lateinit var movie: MovieUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        movie = intent?.getSerializableExtra(MOVIE) as MovieUI

        setupDataBinding()
        subscribeUI()

    }

    override fun onResume() {
        super.onResume()
        movieDetailViewModel.getDetails(movie)
    }

    private fun setupDataBinding() {
        with(binding) {
            lifecycleOwner = this@MovieDetailActivity
            //todo: passar viewmodel ao inves do movie
            movie = this@MovieDetailActivity.movie
            executePendingBindings()
        }
    }

    private fun subscribeUI() {

        with(movieDetailViewModel) {

            movie.observe(this@MovieDetailActivity) {
                binding.movie = it
            }

            onError.observe(this@MovieDetailActivity) {
                it
            }

        }

    }

}
