package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Cast
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun getMovieFromDB(movieId: Int): Flow<MovieEntity>

    suspend fun fetchMovieFromAPI(movieId: Int): Result<Movie>

    fun getMoviesFromDB(category: String): Flow<List<MovieEntity>>

    suspend fun fetchMoviesFromAPI(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>>

    suspend fun fetchCastMovieFromAPI(movieId: Int): Result<List<Cast>>

    suspend fun fetchSearchFromAPI(query: String): Result<List<Movie>>

    suspend fun handleFavorite(movieId: Int)

    fun getFavoritesFromDB(): Flow<List<MovieEntity>>

}