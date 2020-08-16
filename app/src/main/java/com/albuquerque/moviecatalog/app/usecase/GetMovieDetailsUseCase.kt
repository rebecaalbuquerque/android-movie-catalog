package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.app.utils.TypeMovies

class GetMovieDetailsUseCase(
        private val repository: IRepository
) {

    suspend fun invoke(movie: MovieUI): Result<MovieUI> {
        return repository.getMovieDetails(movie.id, movie.category, movie.fetchAt).map {
            it.toEntity(TypeMovies.getByValue(movie.category)).toUI()
        }
    }

}