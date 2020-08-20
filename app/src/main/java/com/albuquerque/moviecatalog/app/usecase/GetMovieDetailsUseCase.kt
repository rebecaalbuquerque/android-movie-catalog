package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.core.extensions.asFlow
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