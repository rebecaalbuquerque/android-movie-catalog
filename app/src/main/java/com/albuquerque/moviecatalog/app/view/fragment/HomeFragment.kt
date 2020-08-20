package com.albuquerque.moviecatalog.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.adapter.MoviesAdapter
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.utils.ImageSliderUtils
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.app.viewmodel.MoviesViewModel
import com.albuquerque.moviecatalog.core.extensions.setGone
import com.albuquerque.moviecatalog.core.extensions.setVisible
import com.albuquerque.moviecatalog.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.empty_movies.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.layoutLoading
import kotlinx.android.synthetic.main.loading_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUI()
        setupView()
        setupDataBinding()
    }

    private fun setupDataBinding() {
        with(binding) {
            lifecycleOwner = this@HomeFragment
            viewModel = moviesViewModel
            executePendingBindings()
        }
    }

    private fun setupView() {

        seeMoreUpcoming.setOnClickListener {
            findNavController().navigate(
                    HomeFragmentDirections.actionSeeMoreMovies(TypeMovies.UPCOMING.value)
            )
        }

        seeMorePopular.setOnClickListener {
            findNavController().navigate(
                    HomeFragmentDirections.actionSeeMoreMovies(TypeMovies.POPULAR.value)
            )
        }

        seeMoreTopRated.setOnClickListener {
            findNavController().navigate(
                    HomeFragmentDirections.actionSeeMoreMovies(TypeMovies.TOP_RATED.value)
            )
        }

        tentarNovamente.setOnClickListener {
            moviesViewModel.getMovies()
        }

    }

    private fun subscribeUI() {

        with(moviesViewModel) {

            nowPlaying.observe(viewLifecycleOwner) { list ->
                ImageSliderUtils(pager, recyclerViewDots, list.map { it.poster }.take(5)).setupViewPager()
            }

            upcoming.observe(viewLifecycleOwner) {
                rvUpcoming.adapter = setupAdapter(it)
            }

            popular.observe(viewLifecycleOwner){
                rvPopular.adapter = setupAdapter(it)
            }

            topRated.observe(viewLifecycleOwner) {
                rvTopRated.adapter = setupAdapter(it)
            }

            onError.observe(viewLifecycleOwner) { erro ->
                container.setGone()
                layoutEmpty.setVisible()

                if(!erro.isNullOrEmpty())
                    text.text = erro
            }

            onStartLoading.observe(viewLifecycleOwner) {
                startLoadingView()
            }

            onFinishLoading.observe(viewLifecycleOwner) {
                stopLoadingView()
            }

        }

    }

    private fun setupAdapter(list: List<MovieUI>): MoviesAdapter {
        return MoviesAdapter(list, {movie ->
            findNavController().navigate(
                    HomeFragmentDirections.actionMovieDetail(movie.id)
            )
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
