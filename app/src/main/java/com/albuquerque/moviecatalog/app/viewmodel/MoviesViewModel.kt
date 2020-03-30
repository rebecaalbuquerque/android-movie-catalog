package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetUpcomingUseCase
import com.albuquerque.moviecatalog.app.usecase.GetNowPlayingUseCase
import com.albuquerque.moviecatalog.app.usecase.GetPopularUseCase
import com.albuquerque.moviecatalog.app.usecase.GetTopRatedUseCase
import com.albuquerque.moviecatalog.core.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class MoviesViewModel(
        val getPopularUseCase: GetPopularUseCase,
        val getNowPlayingUseCase: GetNowPlayingUseCase,
        val getTopRatedUseCase: GetTopRatedUseCase,
        val getUpcomingUseCase: GetUpcomingUseCase
): BaseViewModel() {

    val popular: MutableLiveData<List<MovieUI>> = MutableLiveData()
    val nowPlaying: MutableLiveData<List<MovieUI>> = MutableLiveData()
    val topRated: MutableLiveData<List<MovieUI>> = MutableLiveData()
    val upcoming: MutableLiveData<List<MovieUI>> = MutableLiveData()

    private var requests: Int = 0

    init {
        getMovies()

    }

    private fun getMovies() {
        onLoading.value = true

        viewModelScope.launch {

            try {
                popular.postValue(getPopularUseCase.invoke(FIRST_PAGE_PAGINATION))
            } catch (e: Exception) { handlerError(e) }

            try {
                nowPlaying.postValue(getNowPlayingUseCase.invoke(FIRST_PAGE_PAGINATION))
            } catch (e: Exception){ handlerError(e) }

            try {
                topRated.postValue(getTopRatedUseCase.invoke(FIRST_PAGE_PAGINATION))
            } catch (e: Exception){ handlerError(e) }

            try {
                upcoming.postValue(getUpcomingUseCase.invoke(FIRST_PAGE_PAGINATION))
            } catch (e: Exception){ handlerError(e) }

        }

    }

    private fun updateLoadingStatus(requests: Int) {

    }

}