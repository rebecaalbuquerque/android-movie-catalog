package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {

    suspend fun saveMovies(data: List<MovieEntity>)

    suspend fun saveMovies(data: List<MovieEntity>, typeMovies: TypeMovies)

    suspend fun updateMovie(movie: MovieEntity)

    fun getMoviesByCategory(category: String): Flow<List<MovieEntity>>

    suspend fun getMovie(movieId: Int): MovieEntity?

}