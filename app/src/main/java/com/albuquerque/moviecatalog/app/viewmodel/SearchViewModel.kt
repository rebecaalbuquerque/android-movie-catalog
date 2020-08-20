package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.SearchMoviesUseCase
import com.albuquerque.moviecatalog.core.mediator.SingleMediatorLiveData
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
        private val searchMoviesUseCase: SearchMoviesUseCase
) : BaseViewModel() {

    val onEmptyMovies = MutableLiveData<Boolean>(false)
    val query = MutableLiveData<String>()

    private val _movies = SingleMediatorLiveData<List<MovieUI>>()
    val movies = _movies as LiveData<List<MovieUI>>

    fun fetchSearch(search: String) {
        query.value = search

        viewModelScope.launch {
            searchMoviesUseCase.invokeFromApi(search)
                    .collect { result ->
                        result
                                .onSuccess {
                                    if(it.isEmpty())
                                        onEmptyMovies.postValue(true)
                                    else
                                        _movies.emit(liveData { emit(it) })
                                }
                                .onFailure {
                                    onError.postValue(it.message)
                                }
                    }
        }
    }

    fun clearSearch() {
        query.value = ""
        onEmptyMovies.value = false
    }

}