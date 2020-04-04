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

class MoviesViewModel(
        val getMoviesPaginatedUseCase: GetMoviesPaginatedUseCase
): BaseViewModel() {

    private val pagination = Pagination(0, FIRST_PAGE_PAGINATION)

    val popular: SingleMediatorLiveData<List<MovieUI>> = SingleMediatorLiveData()
    val nowPlaying: SingleMediatorLiveData<List<MovieUI>> = SingleMediatorLiveData()
    val topRated: SingleMediatorLiveData<List<MovieUI>> = SingleMediatorLiveData()
    val upcoming: SingleMediatorLiveData<List<MovieUI>> = SingleMediatorLiveData()

    private var requests: Int = 0

    init {
        getMovies()

    }

    private fun getMovies() {
        onLoading.value = true

        getMoviesPaginatedUseCase.invoke(FIRST_PAGE_PAGINATION, TypeMovies.POPULAR, pagination).onEach {
            popular.emit(it)
        }.catch { error ->
            onError.value = error.message
        }.launchIn(viewModelScope)

        getMoviesPaginatedUseCase.invoke(FIRST_PAGE_PAGINATION, TypeMovies.NOW_PLAYING, pagination).onEach {
            nowPlaying.emit(it)
        }.catch { error ->
            onError.value = error.message
        }.launchIn(viewModelScope)

        getMoviesPaginatedUseCase.invoke(FIRST_PAGE_PAGINATION, TypeMovies.TOP_RATED, pagination).onEach {
            topRated.emit(it)
        }.catch { error ->
            onError.value = error.message
        }.launchIn(viewModelScope)

        getMoviesPaginatedUseCase.invoke(FIRST_PAGE_PAGINATION, TypeMovies.UPCOMING, pagination).onEach {
            upcoming.emit(it)
        }.catch { error ->
            onError.value = error.message
        }.launchIn(viewModelScope)


    }

    private fun updateLoadingStatus(requests: Int) {

    }

}