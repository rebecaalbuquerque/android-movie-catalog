package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.core.remote.IPaginationController
import com.albuquerque.moviecatalog.core.remote.Result

class GetTopRatedUseCase(
        private val repository: IRepository
) {

    suspend fun invoke(page: Int, paginationController: IPaginationController? = null): List<MovieUI> {
        val result = repository.getTopRated(paginationController, page)

        return if(result is Result.Success) {
            result.data.map { it.toUI() }
        } else {
            throw (result as Result.Error).error
        }

    }

}