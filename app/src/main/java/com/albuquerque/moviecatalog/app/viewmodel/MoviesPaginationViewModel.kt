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

class MoviesPaginationViewModel(
        private val getMoviesPaginatedUseCase: GetMoviesPaginatedUseCase
): BaseViewModel() {

    private val pagination = Pagination(FIRST_PAGE_PAGINATION)

    private val _movies = SingleMediatorLiveData<List<MovieUI>>().apply {
        viewModelScope.launch { this@apply.emit(getMoviesPaginatedUseCase.invokeFromDb(TypeMovies.POPULAR, pagination).asLiveData()) }
    }

    val movies = _movies as LiveData<List<MovieUI>>

    fun getMovies(type: TypeMovies) {
        onLoading.postValue(true)

        viewModelScope.launch {
            getMoviesPaginatedUseCase.invokeFromApi(pagination.nextPage, type, pagination).collect { result ->
                onLoading.postValue(false)

                result.onFailure { error ->
                    onSnackBarError.postValue(error.message)
                }

            }
        }

    }

}