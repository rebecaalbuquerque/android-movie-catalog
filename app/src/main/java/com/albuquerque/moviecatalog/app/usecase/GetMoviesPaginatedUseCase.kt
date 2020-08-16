package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.extensions.asFlow
import com.albuquerque.moviecatalog.core.remote.Pagination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetMoviesPaginatedUseCase(
        private val repository: IRepository
) {

    suspend fun invokeFromDb(typeMovies: TypeMovies, paginationController: Pagination) = flow {
        emitAll(
                repository.getMoviesByCategoryFromDB(typeMovies.value)
                        .map { list ->
                            paginationController.hasReachedEndPagination(list.size)
                            list.map { it.toUI() }
                        }
                        .flowOn(Dispatchers.IO)
        )
    }

    suspend fun invokeFromApi(page: Int, typeMovies: TypeMovies, paginationController: Pagination): Flow<Result<List<MovieUI>>> = flow {
        emitAll(
                repository.getMoviesPaginatedByCategory(paginationController, page, typeMovies)
                        .map { list ->
                            list.map { it.toEntity(typeMovies).toUI() }
                        }
                        .asFlow()
                        .flowOn(Dispatchers.IO)
        )

    }

}