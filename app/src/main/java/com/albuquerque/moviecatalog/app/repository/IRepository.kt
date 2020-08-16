package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Cast
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import kotlinx.coroutines.flow.Flow
import java.util.*

interface IRepository {

    suspend fun getMovieFromDB(movieId: Int): MovieEntity?

    suspend fun getMovieDetails(movieId: Int, movieCategory: String, fetchAt: Date): Result<Movie>

    fun getMoviesByCategoryFromDB(category: String): Flow<List<MovieEntity>>

    suspend fun getMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>>

    suspend fun getCastFromMovie(movieId: Int): Result<List<Cast>>

}