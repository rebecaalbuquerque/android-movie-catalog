package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
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