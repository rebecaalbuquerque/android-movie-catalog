package com.albuquerque.moviecatalog.view.fragment

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
import com.albuquerque.moviecatalog.adapter.MoviesAdapter
import com.albuquerque.moviecatalog.utils.ImageSliderUtils
import com.albuquerque.core.util.TypeMovies
import com.albuquerque.moviecatalog.viewmodel.MoviesViewModel
import com.albuquerque.domain.extensions.setGone
import com.albuquerque.domain.extensions.setVisible
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

        rvUpcoming.adapter = MoviesAdapter { movie ->
            findNavController().navigate(
                    HomeFragmentDirections.actionMovieDetail(movie.id)
            )
        }

        rvTopRated.adapter = MoviesAdapter { movie ->
            findNavController().navigate(
                    HomeFragmentDirections.actionMovieDetail(movie.id)
            )
        }

        rvPopular.adapter = MoviesAdapter { movie ->
            findNavController().navigate(
                    HomeFragmentDirections.actionMovieDetail(movie.id)
            )
        }

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

        tentarNovamente.setOnClickListener { moviesViewModel.getMovies() }

    }

    private fun subscribeUI() {

        with(moviesViewModel) {

            nowPlaying.observe(viewLifecycleOwner) { list ->
                ImageSliderUtils(pager, recyclerViewDots, list.map { it.poster }.take(5)).setupViewPager()
            }

            onLayoutError.observe(viewLifecycleOwner) {
                container.setGone()
                layoutEmpty.setVisible()
            }

            onSnackBarError.observe(viewLifecycleOwner) {

            }

            onLoading.observe(viewLifecycleOwner) { loading ->
                if(loading)
                    startLoadingView()
                else
                    stopLoadingView()
            }

        }

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