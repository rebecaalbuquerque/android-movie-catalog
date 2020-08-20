package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.*
import com.albuquerque.moviecatalog.app.data.ui.CastUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetMovieCastUseCase
import com.albuquerque.moviecatalog.app.usecase.GetMovieDetailsUseCase
import com.albuquerque.moviecatalog.core.mediator.SingleMediatorLiveData
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(
        private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
        private val getMovieCastUseCase: GetMovieCastUseCase
) : BaseViewModel() {

    val onMovieLoading = MutableLiveData<Boolean>()
    val onMovieCastLoading = MutableLiveData<Boolean>()

    val onMovieCastError = MutableLiveData<Boolean>(false)

    private val _cast = SingleMediatorLiveData<List<CastUI>>()
    val cast = _cast as LiveData<List<CastUI>>

    private val _movie = SingleMediatorLiveData<MovieUI>()
    val movie = _movie as LiveData<MovieUI>

    var movieId: Int? = null
        set(value) {
            if (value != null) {
                field = value
                getDetails(value)
                getMovieCast(value)
            }
        }

    private fun getDetails(movieId: Int) {
        onMovieLoading.postValue(true)

        viewModelScope.launch {

            getMovieDetailsUseCase.invokeFromApi(movieId)
                    .collect { result ->
                        result
                                .onSuccess {
                                    _movie.emit(getMovieDetailsUseCase.invokeFromDb(movieId).asLiveData())
                                    onMovieLoading.postValue(false)
                                }
                                .onFailure {
                                    onMovieLoading.postValue(false)
                                    onError.postValue(it.message)
                                }
                    }

        }
    }

    private fun getMovieCast(movieId: Int) {
        onMovieCastLoading.postValue(true)

        viewModelScope.launch {
            getMovieCastUseCase.invokeFromApi(movieId).collect { result ->
                result
                        .onSuccess {
                            _cast.emit(liveData { emit(it) })

                            if(it.isEmpty()) onMovieCastError.postValue(true)

                            onMovieCastLoading.postValue(false)
                        }
                        .onFailure {
                            onMovieCastLoading.postValue(false)
                            onMovieCastError.postValue(true)
                            onError.value = it.message
                        }
            }
        }

    }

}