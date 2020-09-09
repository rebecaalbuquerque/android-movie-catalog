package com.albuquerque.domain.repository

import com.albuquerque.data.dto.Cast
import com.albuquerque.data.dto.Movie
import com.albuquerque.domain.remote.Pagination
import com.albuquerque.core.util.TypeMovies

interface IRemoteRepository {

    suspend fun fetchMovie(movieId: Int): Result<Movie>

    suspend fun fetchMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>>

    suspend fun fetchCastFromMovie(movieId: Int): Result<List<Cast>>

    suspend fun fetchSearch(query: String): Result<List<Movie>>

}