package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetUpcomingUseCase
import com.albuquerque.moviecatalog.app.usecase.GetNowPlayingUseCase
import com.albuquerque.moviecatalog.app.usecase.GetPopularUseCase
import com.albuquerque.moviecatalog.app.usecase.GetTopRatedUseCase
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.app.utils.TypeMovies.*
import com.albuquerque.moviecatalog.core.remote.IPaginationController
import com.albuquerque.moviecatalog.core.remote.Remote.Companion.FIRST_PAGE_PAGINATION
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class MoviesPaginationViewModel(
        val getPopularUseCase: GetPopularUseCase,
        val getNowPlayingUseCase: GetNowPlayingUseCase,
        val getTopRatedUseCase: GetTopRatedUseCase,
        val getUpcomingUseCase: GetUpcomingUseCase
): BaseViewModel(), IPaginationController {

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
                    POPULAR    -> getPopularUseCase.invoke(nextPage, this@MoviesPaginationViewModel)
                    NOW_PLAYING -> getNowPlayingUseCase.invoke(nextPage, this@MoviesPaginationViewModel)
                    TOP_RATED   -> getTopRatedUseCase.invoke(nextPage, this@MoviesPaginationViewModel)
                    UPCOMING      -> getUpcomingUseCase.invoke(nextPage, this@MoviesPaginationViewModel)
                }

                movies.postValue(response)

            } catch (e: Exception) { handlerError(e) }
        }

    }

    override fun getNext(refresh: Boolean) {
        getMovies(type!!)
    }

}