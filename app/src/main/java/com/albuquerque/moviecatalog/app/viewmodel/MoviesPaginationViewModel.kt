package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetLatestUseCase
import com.albuquerque.moviecatalog.app.usecase.GetNowPlayingUseCase
import com.albuquerque.moviecatalog.app.usecase.GetPopularUseCase
import com.albuquerque.moviecatalog.app.usecase.GetTopRatedUseCase
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.app.utils.TypeMovies.*
import com.albuquerque.moviecatalog.core.remote.IPaginationController
import com.albuquerque.moviecatalog.core.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import kotlinx.coroutines.launch

class MoviesPaginationViewModel(
        val getPopularUseCase: GetPopularUseCase,
        val getNowPlayingUseCase: GetNowPlayingUseCase,
        val getTopRatedUseCase: GetTopRatedUseCase,
        val getLatestUseCase: GetLatestUseCase
): ViewModel(), IPaginationController {

    // TODO: lógica para impedir request depois da última página
    override var totalPages: Int = 0
    override var nextPage: Int = FIRST_PAGE_PAGINATION

    var type: TypeMovies? = null
    val movies = MutableLiveData<List<MovieUI>>()

    fun getMovies(type: TypeMovies) {
        this.type = type

        viewModelScope.launch {
            try {

                val response = when (type) {
                    POPUPLAR    -> getPopularUseCase.invoke(FIRST_PAGE_PAGINATION, this@MoviesPaginationViewModel)
                    NOW_PLAYING -> getNowPlayingUseCase.invoke(FIRST_PAGE_PAGINATION, this@MoviesPaginationViewModel)
                    TOP_RATED   -> getTopRatedUseCase.invoke(FIRST_PAGE_PAGINATION, this@MoviesPaginationViewModel)
                    LATEST      -> getLatestUseCase.invoke(FIRST_PAGE_PAGINATION, this@MoviesPaginationViewModel)
                }

                movies.postValue(response)

            } catch (e: Exception) {
                e
            }
        }

    }

    override fun getNext(refresh: Boolean) {

    }

}