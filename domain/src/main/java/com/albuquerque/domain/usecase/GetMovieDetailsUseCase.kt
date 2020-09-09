package com.albuquerque.domain.usecase

import com.albuquerque.data.toEntity
import com.albuquerque.data.toUI
import com.albuquerque.data.ui.MovieUI
import com.albuquerque.domain.extensions.asFlow
import com.albuquerque.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetMovieDetailsUseCase(
        private val repository: IRepository
) {

    suspend fun invokeFromDb(movieId: Int): Flow<MovieUI> = flow {
        emitAll(
                repository.getMovieFromDB(movieId)
                        .map { it.toUI() }
                        .flowOn(Dispatchers.IO)
        )
    }

    suspend fun invokeFromApi(movieId: Int): Flow<Result<MovieUI>> = flow {
        emitAll(
                repository.fetchMovieFromAPI(movieId)
                        .map {
                            it.toEntity().toUI()
                        }
                        .asFlow()
                        .flowOn(Dispatchers.IO)
        )
    }

}