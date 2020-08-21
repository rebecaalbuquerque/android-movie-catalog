package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetMoviesPaginatedUseCase
import com.albuquerque.moviecatalog.app.utils.TypeMovies.*
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
            getMoviesPaginatedUseCase.invokeFromDb(POPULAR, pagination).collect { result ->
                if(result.isEmpty())
                    onLayoutError.postValue(Any())
                else
                    this@apply.emit(liveData { emit(result) })
            }
        }
    }

    private val _nowPlaying = SingleMediatorLiveData<List<MovieUI>>().apply {
        viewModelScope.launch {
            getMoviesPaginatedUseCase.invokeFromDb(NOW_PLAYING, pagination).collect { result ->
                if(result.isEmpty())
                    onLayoutError.postValue(Any())
                else
                    this@apply.emit(liveData { emit(result) })
            }
        }
    }

    private val _topRated = SingleMediatorLiveData<List<MovieUI>>().apply {
        viewModelScope.launch {
            getMoviesPaginatedUseCase.invokeFromDb(TOP_RATED, pagination).collect { result ->
                if(result.isEmpty())
                    onLayoutError.postValue(Any())
                else
                    this@apply.emit(liveData { emit(result) })
            }
        }
    }

    private val _upcoming = SingleMediatorLiveData<List<MovieUI>>().apply {
        viewModelScope.launch {
            getMoviesPaginatedUseCase.invokeFromDb(UPCOMING, pagination).collect { result ->
                if(result.isEmpty())
                    onLayoutError.postValue(Any())
                else
                    this@apply.emit(liveData { emit(result) })
            }
        }
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
        onLoading.postValue(true)

        viewModelScope.launch {
            getMoviesPaginatedUseCase.invokeFromApi(FIRST_PAGE_PAGINATION, NOW_PLAYING, pagination).collect { result ->
                checkLoadedCategories()

                result.onFailure { error ->
                    onSnackBarError.postValue(error.message)
                }
            }

            getMoviesPaginatedUseCase.invokeFromApi(FIRST_PAGE_PAGINATION, UPCOMING, pagination).collect { result ->
                checkLoadedCategories()

                result.onFailure { error ->
                    onSnackBarError.postValue(error.message)
                }
            }

            getMoviesPaginatedUseCase.invokeFromApi(FIRST_PAGE_PAGINATION, POPULAR, pagination).collect { result ->
                checkLoadedCategories()

                result.onFailure { error ->
                    onSnackBarError.postValue(error.message)
                }
            }

            getMoviesPaginatedUseCase.invokeFromApi(FIRST_PAGE_PAGINATION, TOP_RATED, pagination).collect { result ->
                checkLoadedCategories()

                result.onFailure { error ->
                    onSnackBarError.postValue(error.message)
                }
            }
        }

    }

    private fun checkLoadedCategories() {
        categories -= 1

        if(categories == 0)
            onLoading.postValue(false)
    }

}