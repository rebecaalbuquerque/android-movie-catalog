package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetMovieDetailsUseCase
import com.albuquerque.moviecatalog.core.mediator.SingleMediatorLiveData
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(
        private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    val onLoading = MutableLiveData<Boolean>()

    private val _movie = SingleMediatorLiveData<MovieUI>()
    val movie = _movie as LiveData<MovieUI>

    var movieId: Int? = null
        set(value) {
            if (value != null) {
                field = value
                getDetails(value)
            }
        }

    private fun getDetails(movieId: Int) {
        onLoading.postValue(true)

        viewModelScope.launch {

            getMovieDetailsUseCase.invokeFromApi(movieId)
                    .collect { result ->
                        result
                                .onSuccess {
                                    onLoading.postValue(false)
                                    _movie.emit(getMovieDetailsUseCase.invokeFromDb(movieId).asLiveData())
                                }
                                .onFailure {
                                    onLoading.postValue(false)
                                    onError.value = it.message
                                }
                    }

        }
    }

}