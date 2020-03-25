package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetPopularUseCase
import com.albuquerque.moviecatalog.core.remote.IPaginationController
import com.albuquerque.moviecatalog.core.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import kotlinx.coroutines.launch

class MoviesViewModel(
        val getPopularUseCase: GetPopularUseCase
): ViewModel(), IPaginationController {

    // TODO: lógica para impedir request depois da última página
    override var totalPages: Int = 0
    override var nextPage: Int = FIRST_PAGE_PAGINATION

    val movies: MutableLiveData<List<MovieUI>> = MutableLiveData()

    init {
        getPopular()
    }

    private fun getPopular() {

        viewModelScope.launch {

            try {
                movies.postValue(getPopularUseCase.invoke(this@MoviesViewModel, nextPage))
            } catch (e: Exception) {
                // TODO:
                e
            }

        }

    }

    override fun getNext(refresh: Boolean) {
        getPopular()
    }

}