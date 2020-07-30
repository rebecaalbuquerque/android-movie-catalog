package com.albuquerque.moviecatalog.app.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class GetMoviesPaginatedUseCase(
        private val repository: IRepository
) {

    fun invoke(page: Int, typeMovies: TypeMovies, paginationController: Pagination): Flow<LiveData<List<MovieUI>>> = flow {

        emit(
                repository.getMoviesByCategoryFromDB(typeMovies.value).map { list ->
                    paginationController.hasReachedEndPagination(list.size)
                    list.map { it.toUI() }
                }
        )

        val result = withContext(coroutineContext) {
            repository.getMoviesPaginatedByCategory(paginationController, page, typeMovies)
        }

        if(result is Result.Error) throw result.error

    }

}