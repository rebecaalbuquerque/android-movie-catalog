package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.CastUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.core.extensions.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieCastUseCase(
        private val repository: IRepository
) {

    suspend fun invokeFromApi(movieId: Int): Flow<Result<List<CastUI>>> = flow {
        emitAll(
                repository.fetchCastMovieFromAPI(movieId)
                        .map { list -> list.map { it.toEntity().toUI() } }
                        .asFlow()
                        .flowOn(Dispatchers.IO)
        )
    }

}