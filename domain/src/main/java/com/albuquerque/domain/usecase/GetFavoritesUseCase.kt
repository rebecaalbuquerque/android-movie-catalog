package com.albuquerque.domain.usecase

import com.albuquerque.data.toUI
import com.albuquerque.data.ui.MovieUI
import com.albuquerque.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetFavoritesUseCase(
        private val repository: IRepository
) {

    suspend fun invoke(): Flow<List<MovieUI>> = flow {
        emitAll(
                repository.getFavoritesFromDB().map { list -> list.map { it.toUI() } }.flowOn(Dispatchers.IO)
        )
    }

}