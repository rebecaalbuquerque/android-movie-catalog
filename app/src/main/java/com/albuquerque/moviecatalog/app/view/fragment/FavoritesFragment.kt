package com.albuquerque.moviecatalog.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.adapter.MoviesAdapter
import com.albuquerque.moviecatalog.app.viewmodel.FavoritesViewModel
import com.albuquerque.moviecatalog.databinding.FragmentFavoritesBinding
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val favoritesViewModel: FavoritesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFavorites.adapter = MoviesAdapter(MoviesAdapter.TypeMovieView.LINEAR) {
            // todo
        }

        setupDatabinding()
    }

    private fun setupDatabinding() {
        with(binding) {
            lifecycleOwner = this@FavoritesFragment
            viewModel = favoritesViewModel
            executePendingBindings()
        }
    }

}
