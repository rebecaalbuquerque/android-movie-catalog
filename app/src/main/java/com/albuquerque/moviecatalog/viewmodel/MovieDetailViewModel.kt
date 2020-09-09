package com.albuquerque.moviecatalog.viewmodel

import androidx.lifecycle.*
import com.albuquerque.data.ui.CastUI
import com.albuquerque.data.ui.MovieUI
import com.albuquerque.domain.usecase.GetMovieCastUseCase
import com.albuquerque.domain.usecase.GetMovieDetailsUseCase
import com.albuquerque.domain.usecase.ToggleFavoriteUseCase
import com.albuquerque.core.mediator.SingleMediatorLiveData
import com.albuquerque.core.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(
        private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
        private val getMovieCastUseCase: GetMovieCastUseCase,
        private val toggleFavoriteUseCase: ToggleFavoriteUseCase
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

                        onMovieLoading.postValue(false)

                        result
                                .onSuccess {
                                    _movie.emit(getMovieDetailsUseCase.invokeFromDb(movieId).asLiveData())
                                }
                                .onFailure {
                                    _movie.emit(getMovieDetailsUseCase.invokeFromDb(movieId).asLiveData())
                                    onSnackBarError.postValue(it.message)
                                }
                    }

        }
    }

    private fun getMovieCast(movieId: Int) {
        onMovieCastLoading.postValue(true)

        viewModelScope.launch {
            getMovieCastUseCase.invokeFromApi(movieId).collect { result ->
                onMovieCastLoading.postValue(false)

                result
                        .onSuccess {
                            _cast.emit(liveData { emit(it) })
                            if(it.isEmpty()) onMovieCastError.postValue(true)
                        }
                        .onFailure {
                            onMovieCastError.postValue(true)
                            onSnackBarError.value = it.message
                        }
            }
        }

    }

    fun handleFavorite() {
        movieId?.let {  id ->
            viewModelScope.launch(Dispatchers.IO) {
                toggleFavoriteUseCase.invoke(id)
            }
        }
    }

}