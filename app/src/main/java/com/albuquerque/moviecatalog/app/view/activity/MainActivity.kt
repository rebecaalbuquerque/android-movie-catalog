package com.albuquerque.moviecatalog.app.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity: AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeUI()

        btn.setOnClickListener{
            moviesViewModel.getNext()
        }
    }

    private fun subscribeUI() {

        with(moviesViewModel) {
            movies.observe(this@MainActivity) {
                it
            }
        }

    }

}