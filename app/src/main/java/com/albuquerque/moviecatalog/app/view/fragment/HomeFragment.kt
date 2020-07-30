package com.albuquerque.moviecatalog.app.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.adapter.MoviesAdapter
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.utils.ImageSliderUtils
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.app.view.activity.MovieDetailActivity
import com.albuquerque.moviecatalog.app.view.activity.MovieDetailActivity.Companion.MOVIE
import com.albuquerque.moviecatalog.app.view.activity.SeeMoreMoviesActivity
import com.albuquerque.moviecatalog.app.view.activity.SeeMoreMoviesActivity.Companion.TYPE_MOVIE
import com.albuquerque.moviecatalog.app.viewmodel.MoviesViewModel
import com.albuquerque.moviecatalog.core.extensions.setGone
import com.albuquerque.moviecatalog.core.extensions.setVisible
import kotlinx.android.synthetic.main.empty_movies.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.layoutLoading
import kotlinx.android.synthetic.main.loading_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUI()
        setupView()
    }

    private fun setupView() {

        seeMoreUpcoming.setOnClickListener {
            startActivity(Intent(context, SeeMoreMoviesActivity::class.java).apply { putExtra(TYPE_MOVIE, TypeMovies.UPCOMING.value) })
        }

        seeMorePopular.setOnClickListener {
            startActivity(Intent(context, SeeMoreMoviesActivity::class.java).apply { putExtra(TYPE_MOVIE, TypeMovies.POPULAR.value) })
        }

        seeMoreTopRated.setOnClickListener {
            startActivity(Intent(context, SeeMoreMoviesActivity::class.java).apply { putExtra(TYPE_MOVIE, TypeMovies.TOP_RATED.value) })
        }

        tentarNovamente.setOnClickListener {
            moviesViewModel.getMovies()
        }

    }

    private fun subscribeUI() {

        with(moviesViewModel) {

            nowPlaying.observe(viewLifecycleOwner) { list ->
                list?.let {
                    ImageSliderUtils(pager, recyclerViewDots, list.map { it.poster }.take(5)).setupViewPager()
                    checkLoadedCategories()
                }
            }

            upcoming.observe(viewLifecycleOwner) { list ->
                list?.let {
                    rvUpcoming.adapter = setupAdapter(it)
                    checkLoadedCategories()
                }
            }

            popular.observe(viewLifecycleOwner){ list ->
                list?.let {
                    rvPopular.adapter = setupAdapter(it)
                    checkLoadedCategories()
                }
            }

            topRated.observe(viewLifecycleOwner) { list ->
                list?.let {
                    rvTopRated.adapter = setupAdapter(it)
                    checkLoadedCategories()
                }
            }

            onError.observe(viewLifecycleOwner) { erro ->
                container.setGone()
                layoutEmpty.setVisible()

                if(!erro.isNullOrEmpty())
                    text.text = erro
            }

            onStartLoading.observe(this@HomeFragment) {
                startLoadingView()
            }

            onFinishLoading.observe(this@HomeFragment) {
                stopLoadingView()
            }

        }

    }

    private fun setupAdapter(list: List<MovieUI>): MoviesAdapter {
        return MoviesAdapter(list, {movie ->
            startActivity(Intent(context, MovieDetailActivity::class.java).apply { putExtra(MOVIE, movie) })
        })
    }

    private fun startLoadingView() {
        container.setGone()
        layoutEmpty.setGone()
        layoutLoading.setVisible()

        val animation = AlphaAnimation(1.0f, 0.6f).apply {
            this.duration = 900
            this.startOffset = 20
            this.repeatMode = Animation.REVERSE
            this.repeatCount = Animation.INFINITE
        }

        listOf(loadingSlider, loadingCategory, loadingSeeMore, layout1, layout2, layout3, layout4).forEach {
            it.startAnimation(animation)
        }
    }

    private fun stopLoadingView() {
        listOf(loadingSlider, loadingCategory, loadingSeeMore, layout1, layout2, layout3, layout4).forEach {
            it.animation?.cancel()
        }

        layoutLoading.setGone()
        container.setVisible()
    }

}
