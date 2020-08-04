package com.albuquerque.moviecatalog.app.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "MOVIE"
    }

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        setupDataBinding()

    }

    private fun setupDataBinding() {
        with(binding) {
            lifecycleOwner = this@MovieDetailActivity
            movie = intent?.getSerializableExtra(MOVIE) as MovieUI
            executePendingBindings()
        }
    }

}
