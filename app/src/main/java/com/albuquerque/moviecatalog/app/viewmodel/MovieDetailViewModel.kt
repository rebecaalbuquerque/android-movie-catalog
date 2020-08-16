package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetMovieDetailsUseCase
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
        private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): BaseViewModel() {

    val movie = MutableLiveData<MovieUI>()

    fun getDetails(movieUI: MovieUI) {

        viewModelScope.launch {

            getMovieDetailsUseCase.invoke(movieUI)
                    .onSuccess { movie.value = it }
                    .onFailure {
                        movie.value = movieUI
                        onError.value = it.message
                    }

        }
    }

}