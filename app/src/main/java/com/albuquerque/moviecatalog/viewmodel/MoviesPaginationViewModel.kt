package com.albuquerque.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.data.ui.MovieUI
import com.albuquerque.domain.usecase.GetMoviesPaginatedUseCase
import com.albuquerque.core.util.TypeMovies
import com.albuquerque.core.mediator.SingleMediatorLiveData
import com.albuquerque.domain.remote.Pagination
import com.albuquerque.domain.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import com.albuquerque.core.viewmodel.BaseViewModel
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