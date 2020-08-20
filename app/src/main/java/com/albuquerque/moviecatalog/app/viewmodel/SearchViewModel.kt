package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.SearchMoviesUseCase
import com.albuquerque.moviecatalog.app.utils.StatusSearch
import com.albuquerque.moviecatalog.core.mediator.SingleMediatorLiveData
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
        private val searchMoviesUseCase: SearchMoviesUseCase
) : BaseViewModel() {

    val query = MutableLiveData<String>()
    val statusSearch = MutableLiveData<StatusSearch>()

    private val _movies = SingleMediatorLiveData<List<MovieUI>>()
    val movies = _movies as LiveData<List<MovieUI>>

    init {
        statusSearch.value = StatusSearch.BEFORE_SEARCH
    }

    fun fetchSearch(search: String) {
        query.value = search

        viewModelScope.launch {
            searchMoviesUseCase.invokeFromApi(search)
                    .collect { result ->
                        result
                                .onSuccess {
                                    if(it.isEmpty())
                                        statusSearch.postValue(StatusSearch.EMPTY)
                                    else {
                                        statusSearch.postValue(StatusSearch.SUCCESS)
                                        _movies.emit(liveData { emit(it) })
                                    }
                                }
                                .onFailure {
                                    statusSearch.postValue(StatusSearch.ERROR)
                                    onError.postValue(it.message)
                                }
                    }
        }
    }

    fun clearSearch() {
        query.value = ""
        _movies.emit(MutableLiveData(emptyList()))
        statusSearch.value = StatusSearch.BEFORE_SEARCH
    }

}