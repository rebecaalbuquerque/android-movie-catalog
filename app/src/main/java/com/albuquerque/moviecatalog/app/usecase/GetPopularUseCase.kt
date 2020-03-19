package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.model.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository

class GetPopularUseCase(
        private val repository: IRepository
) {

    suspend fun invoke(): List<MovieUI> {
        repository.getPopular()
        return emptyList()
    }

}