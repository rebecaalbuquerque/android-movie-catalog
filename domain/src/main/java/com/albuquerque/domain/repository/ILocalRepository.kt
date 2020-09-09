package com.albuquerque.domain.repository

import com.albuquerque.data.entity.MovieEntity
import com.albuquerque.core.util.TypeMovies
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {

    suspend fun saveMovies(data: List<MovieEntity>)

    suspend fun saveMovies(data: List<MovieEntity>, typeMovies: TypeMovies)

    suspend fun updateMovie(movie: MovieEntity)

    fun getMoviesByCategory(category: String): Flow<List<MovieEntity>>

    fun getMovieAsFlow(movieId: Int): Flow<MovieEntity>

    suspend fun getMovie(movieId: Int): MovieEntity?

    fun getFavorites(): Flow<List<MovieEntity>>

}