package com.albuquerque.domain.usecase

import com.albuquerque.data.toEntity
import com.albuquerque.data.toUI
import com.albuquerque.data.ui.CastUI
import com.albuquerque.domain.extensions.asFlow
import com.albuquerque.domain.repository.IRepository
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