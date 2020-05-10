package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetMoviesPaginatedUseCase
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.mediator.SingleMediatorLiveData
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MoviesPaginationViewModel(
        private val getMoviesPaginatedUseCase: GetMoviesPaginatedUseCase
): BaseViewModel() {

    private val pagination = Pagination(FIRST_PAGE_PAGINATION)

    val movies: SingleMediatorLiveData<List<MovieUI>> = SingleMediatorLiveData()

    fun getMovies(type: TypeMovies) {

        getMoviesPaginatedUseCase.invoke(pagination.nextPage, type, pagination).onEach {
            if(!pagination.hasLoadLastPage)
                movies.emit(it)
        }.catch { error ->
            error
        }.launchIn(viewModelScope)

    }

    /*override fun getNext(refresh: Boolean) {
        getMovies(type!!)
    }*/

}