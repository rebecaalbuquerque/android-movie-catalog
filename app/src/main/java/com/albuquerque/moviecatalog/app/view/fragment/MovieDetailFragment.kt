package com.albuquerque.moviecatalog.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.adapter.CastAdapter
import com.albuquerque.moviecatalog.app.viewmodel.MovieDetailViewModel
import com.albuquerque.moviecatalog.databinding.FragmentMovieDetailBinding
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailFragment : Fragment() {

    private val safeArgs: MovieDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private val castAdapter = CastAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel.movieId = safeArgs.movieId
        rvCast.adapter = castAdapter

        backButton.setOnClickListener {
            with(findNavController()) {
                (this.previousBackStackEntry?.destination as? FragmentNavigator.Destination)?.className?.let { destination ->
                    when(destination) {
                        FavoritesFragment::class.java.name -> this.navigate(R.id.back_favorites_action)
                        SearchFragment::class.java.name -> this.navigate(R.id.back_search_action)
                        SeeMoreMoviesFragment::class.java.name -> this.navigate(R.id.back_see_more_action)
                        else -> this.navigate(R.id.back_home_action)
                    }
                }
            }
        }

        setupDataBinding()
        subscribeUI()
    }

    private fun setupDataBinding() {
        with(binding) {
            lifecycleOwner = this@MovieDetailFragment
            viewModel = movieDetailViewModel
            executePendingBindings()
        }
    }

    private fun subscribeUI() {
        with(movieDetailViewModel) {

        }
    }

}