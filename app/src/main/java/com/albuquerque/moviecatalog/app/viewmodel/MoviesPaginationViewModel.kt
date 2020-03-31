package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetUpcomingUseCase
import com.albuquerque.moviecatalog.app.usecase.GetNowPlayingUseCase
import com.albuquerque.moviecatalog.app.usecase.GetPopularUseCase
import com.albuquerque.moviecatalog.app.usecase.GetTopRatedUseCase
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.app.utils.TypeMovies.*
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class MoviesPaginationViewModel(
        val getPopularUseCase: GetPopularUseCase,
        val getNowPlayingUseCase: GetNowPlayingUseCase,
        val getTopRatedUseCase: GetTopRatedUseCase,
        val getUpcomingUseCase: GetUpcomingUseCase
): BaseViewModel() {

    private val pagination = Pagination(0, FIRST_PAGE_PAGINATION)

    val movies = MutableLiveData<List<MovieUI>>()

    fun getMovies(type: TypeMovies) {

        viewModelScope.launch {
            try {

                val response = when (type) {
                    POPULAR     -> getPopularUseCase.invoke(pagination.nextPage, pagination)
                    NOW_PLAYING -> getNowPlayingUseCase.invoke(pagination.nextPage, pagination)
                    TOP_RATED   -> getTopRatedUseCase.invoke(pagination.nextPage, pagination)
                    UPCOMING    -> getUpcomingUseCase.invoke(pagination.nextPage, pagination)
                }

                movies.postValue(response)

            } catch (e: Exception) { handlerError(e) }
        }

    }

    /*override fun getNext(refresh: Boolean) {
        getMovies(type!!)
    }*/

}