package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.core.extensions.asFlow
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
                repository.fetchSearch(query)
                        .map { list -> list.map { it.toUI() } }
                        .asFlow()
                        .flowOn(Dispatchers.IO)
        )
    }

}