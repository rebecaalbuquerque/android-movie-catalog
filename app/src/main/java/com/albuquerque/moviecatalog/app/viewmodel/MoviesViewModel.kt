package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetLatestUseCase
import com.albuquerque.moviecatalog.app.usecase.GetNowPlayingUseCase
import com.albuquerque.moviecatalog.app.usecase.GetPopularUseCase
import com.albuquerque.moviecatalog.app.usecase.GetTopRatedUseCase
import com.albuquerque.moviecatalog.core.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import kotlinx.coroutines.launch

class MoviesViewModel(
        val getPopularUseCase: GetPopularUseCase,
        val getNowPlayingUseCase: GetNowPlayingUseCase,
        val getTopRatedUseCase: GetTopRatedUseCase,
        val getLatestUseCase: GetLatestUseCase
): ViewModel() {

    val popular: MutableLiveData<List<MovieUI>> = MutableLiveData()
    val nowPlaying: MutableLiveData<List<MovieUI>> = MutableLiveData()
    val topRated: MutableLiveData<List<MovieUI>> = MutableLiveData()
    val latest: MutableLiveData<List<MovieUI>> = MutableLiveData()

    init {
        getMovies()
    }

    private fun getMovies() {

        viewModelScope.launch {

            try {
                popular.postValue(getPopularUseCase.invoke(FIRST_PAGE_PAGINATION))
            } catch (e: Exception) {
                // TODO:
                e
            }

            try {
                nowPlaying.postValue(getNowPlayingUseCase.invoke(FIRST_PAGE_PAGINATION))
            } catch (e: Exception) {
                // TODO:
                e
            }

            try {
                topRated.postValue(getTopRatedUseCase.invoke(FIRST_PAGE_PAGINATION))
            } catch (e: Exception) {
                // TODO:
                e
            }

            try {
                latest.postValue(getLatestUseCase.invoke(FIRST_PAGE_PAGINATION))
            } catch (e: Exception) {
                // TODO:
                e
            }

        }

    }

}