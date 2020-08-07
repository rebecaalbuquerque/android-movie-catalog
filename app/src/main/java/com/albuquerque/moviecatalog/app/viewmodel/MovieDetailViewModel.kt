package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetMovieDetailsUseCase
import com.albuquerque.moviecatalog.core.remote.Result
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
        private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): BaseViewModel() {

    val movie = MutableLiveData<MovieUI>()

    fun getDetails(movieUI: MovieUI) {

        viewModelScope.launch {

            when (val result = getMovieDetailsUseCase.invoke(movieUI)) {

                is Result.Success -> {
                    movie.value = result.data
                }

                is Result.Error -> {
                    movie.value = movieUI
                    onError.value = ""
                }

            }

        }
    }

}