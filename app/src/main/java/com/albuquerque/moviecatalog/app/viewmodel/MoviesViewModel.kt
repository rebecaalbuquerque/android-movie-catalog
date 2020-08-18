package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetMoviesPaginatedUseCase
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.mediator.SingleMediatorLiveData
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesViewModel(
        val getMoviesPaginatedUseCase: GetMoviesPaginatedUseCase
): BaseViewModel() {

    private val pagination = Pagination(FIRST_PAGE_PAGINATION)
    private var categories: Int = 0

    private val _popular = SingleMediatorLiveData<List<MovieUI>>().apply {
        viewModelScope.launch {
            this@apply.emit(getMoviesPaginatedUseCase.invokeFromDb(TypeMovies.POPULAR, pagination).asLiveData())
        }
    }

    private val _nowPlaying = SingleMediatorLiveData<List<MovieUI>>().apply {
        viewModelScope.launch { this@apply.emit(getMoviesPaginatedUseCase.invokeFromDb(TypeMovies.NOW_PLAYING, pagination).asLiveData()) }
    }

    private val _topRated = SingleMediatorLiveData<List<MovieUI>>().apply {
        viewModelScope.launch { this@apply.emit(getMoviesPaginatedUseCase.invokeFromDb(TypeMovies.TOP_RATED, pagination).asLiveData()) }
    }

    private val _upcoming = SingleMediatorLiveData<List<MovieUI>>().apply {
        viewModelScope.launch { this@apply.emit(getMoviesPaginatedUseCase.invokeFromDb(TypeMovies.UPCOMING, pagination).asLiveData()) }
    }

    val popular = _popular as LiveData<List<MovieUI>>
    val nowPlaying = _nowPlaying as LiveData<List<MovieUI>>
    val topRated = _topRated as LiveData<List<MovieUI>>
    val upcoming = _upcoming as LiveData<List<MovieUI>>

    init {
        getMovies()
    }

    fun getMovies() {
        categories = 4
        onStartLoading.value = Any()

        viewModelScope.launch {
            getMoviesPaginatedUseCase.invokeFromApi(FIRST_PAGE_PAGINATION,TypeMovies.NOW_PLAYING, pagination).collect { result ->
                checkLoadedCategories()

                if(result.isFailure) {
                    onError.postValue(result.exceptionOrNull()?.message ?: "")
                }
            }

            getMoviesPaginatedUseCase.invokeFromApi(FIRST_PAGE_PAGINATION,TypeMovies.UPCOMING, pagination).collect { result ->
                checkLoadedCategories()
                if(result.isFailure) {
                    onError.postValue(result.exceptionOrNull()?.message ?: "")
                }
            }

            getMoviesPaginatedUseCase.invokeFromApi(FIRST_PAGE_PAGINATION,TypeMovies.POPULAR, pagination).collect { result ->
                checkLoadedCategories()
                if(result.isFailure) {
                    onError.postValue(result.exceptionOrNull()?.message ?: "")
                }
            }

            getMoviesPaginatedUseCase.invokeFromApi(FIRST_PAGE_PAGINATION,TypeMovies.TOP_RATED, pagination).collect { result ->
                checkLoadedCategories()
                if(result.isFailure) {
                    onError.postValue(result.exceptionOrNull()?.message ?: "")
                }
            }
        }

    }

    private fun checkLoadedCategories() {
        categories -= 1

        if(categories == 0)
            onFinishLoading.value = Any()
    }

}