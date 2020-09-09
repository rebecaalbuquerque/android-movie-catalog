package com.albuquerque.domain.usecase

import com.albuquerque.data.toUI
import com.albuquerque.data.ui.MovieUI
import com.albuquerque.domain.extensions.asFlow
import com.albuquerque.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchMoviesUseCase(
        private val repository: IRepository
) {

    suspend fun invokeFromApi(query: String): Flow<Result<List<MovieUI>>> = flow {
        emitAll(
                repository.fetchSearchFromAPI(query)
                        .map { list -> list.map { it.toUI() } }
                        .asFlow()
                        .flowOn(Dispatchers.IO)
        )
    }

}