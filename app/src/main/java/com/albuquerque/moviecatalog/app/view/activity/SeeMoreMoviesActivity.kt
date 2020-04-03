package com.albuquerque.moviecatalog.app.view.activity

import android.os.Bundle
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.adapter.MoviesAdapter
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.app.viewmodel.MoviesPaginationViewModel
import com.albuquerque.moviecatalog.core.view.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_see_more_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeeMoreMoviesActivity : BaseActivity() {

    companion object { const val TYPE_MOVIE = "TYPE_MOVIE" }

    private var typeMovies: TypeMovies? = null
    private val moviesViewModel: MoviesPaginationViewModel by viewModel()
    private val moviesAdapter = MoviesAdapter(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_more_movies)

        this.setSupportActionBar(toolbar)

        setupView()
        subscribeUI()

    }

    override fun onResume() {
        super.onResume()
        typeMovies?.let { moviesViewModel.getMovies(it) }

    }

    private fun setupView() {
        intent?.getStringExtra(TYPE_MOVIE)?.let {
            typeMovies = TypeMovies.getByValue(it)

            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = it
            }

        }

        with(rvMovies) {
            adapter = moviesAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1) && newState == 0) {
                        moviesViewModel.getMovies(typeMovies!!)
                    }

                }
            })
        }

    }

    private fun subscribeUI() {

        with(moviesViewModel) {

            movies.observe(this@SeeMoreMoviesActivity) { list ->
                list?.let { moviesAdapter.refresh(it) }
            }

        }

    }

}
