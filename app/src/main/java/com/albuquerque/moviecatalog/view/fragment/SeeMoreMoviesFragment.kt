package com.albuquerque.moviecatalog.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.adapter.MoviesAdapter
import com.albuquerque.core.util.TypeMovies
import com.albuquerque.moviecatalog.viewmodel.MoviesPaginationViewModel
import com.albuquerque.moviecatalog.databinding.FragmentSeeMoreMoviesBinding
import kotlinx.android.synthetic.main.fragment_see_more_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SeeMoreMoviesFragment : Fragment() {

    private val safeArgs: SeeMoreMoviesFragmentArgs by navArgs()
    private lateinit var binding: FragmentSeeMoreMoviesBinding
    private val moviesViewModel: MoviesPaginationViewModel by viewModel()
    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var typeMovies: TypeMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        typeMovies = TypeMovies.getByValue(safeArgs.typeMovies)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_see_more_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDatabinding()
        setupView()
        subscribeUI()
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.getMovies(typeMovies)
    }

    private fun setupView() {
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = typeMovies.value
        }

        moviesAdapter = MoviesAdapter(MoviesAdapter.TypeMovieView.GRID) { movie ->
            findNavController().navigate(
                    SeeMoreMoviesFragmentDirections.actionMovieDetail(movie.id)
            )
        }

        with(rvMovies) {
            adapter = moviesAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1) && newState == 0) {
                        moviesViewModel.getMovies(typeMovies)
                    }

                }
            })
        }

    }

    private fun subscribeUI() {

        with(moviesViewModel) {

            movies.observe(viewLifecycleOwner) {
                moviesAdapter.refresh(it)
            }

            onSnackBarError.observe(viewLifecycleOwner) {

            }

            onLoading.observe(viewLifecycleOwner) {
                // todo
            }

        }

    }

    private fun setupDatabinding() {
        with(binding) {
            lifecycleOwner = this@SeeMoreMoviesFragment
            viewModel = moviesViewModel
            executePendingBindings()
        }
    }

}