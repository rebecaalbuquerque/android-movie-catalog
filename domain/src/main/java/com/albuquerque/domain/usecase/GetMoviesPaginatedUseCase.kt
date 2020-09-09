package com.albuquerque.domain.usecase

import com.albuquerque.data.toEntity
import com.albuquerque.data.toUI
import com.albuquerque.data.ui.MovieUI
import com.albuquerque.domain.extensions.asFlow
import com.albuquerque.domain.remote.Pagination
import com.albuquerque.domain.repository.IRepository
import com.albuquerque.core.util.TypeMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetMoviesPaginatedUseCase(
        private val repository: IRepository
) {

    suspend fun invokeFromDb(typeMovies: TypeMovies, paginationController: Pagination) = flow {
        emitAll(
                repository.getMoviesFromDB(typeMovies.value)
                        .map { list ->
                            paginationController.hasReachedEndPagination(list.size)
                            list.map { it.toUI() }
                        }
                        .flowOn(Dispatchers.IO)
        )
    }

    suspend fun invokeFromApi(page: Int, typeMovies: TypeMovies, paginationController: Pagination): Flow<Result<List<MovieUI>>> = flow {
        emitAll(
                repository.fetchMoviesFromAPI(paginationController, page, typeMovies)
                        .map { list ->
                            list.map { it.toEntity(typeMovies).toUI() }
                        }
                        .asFlow()
                        .flowOn(Dispatchers.IO)
        )

    }

}