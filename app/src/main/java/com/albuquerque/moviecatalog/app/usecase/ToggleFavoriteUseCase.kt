package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.repository.IRepository

class ToggleFavoriteUseCase(
        private val repository: IRepository
) {

    suspend fun invoke(movieId: Int) {
        repository.handleFavorite(movieId)
    }

}