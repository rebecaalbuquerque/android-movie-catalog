package com.albuquerque.moviecatalog.app.view.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.adapter.MoviesAdapter
import com.albuquerque.moviecatalog.app.viewmodel.SearchViewModel
import com.albuquerque.moviecatalog.core.widget.SearchView
import com.albuquerque.moviecatalog.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val menuItem = menu.findItem(R.id.action_search_movies).apply {
            setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    searchViewModel.clearSearch()
                    return true
                }
            })
        }

        (menuItem?.actionView as? SearchView)?.apply {

            setOnQueryTextSubmit {
                searchViewModel.fetchSearch(it)
            }



        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies.adapter = MoviesAdapter(MoviesAdapter.TypeMovieView.GRID) {}

        setupDatabinding()
    }

    private fun setupDatabinding() {
        with(binding) {
            lifecycleOwner = this@SearchFragment
            viewModel = searchViewModel
            executePendingBindings()
        }
    }

}
