package com.albuquerque.domain.usecase

import com.albuquerque.domain.repository.IRepository

class ToggleFavoriteUseCase(
        private val repository: IRepository
) {

    suspend fun invoke(movieId: Int) {
        repository.handleFavorite(movieId)
    }

}