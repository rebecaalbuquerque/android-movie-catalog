package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result

interface IRepository {

    suspend fun getMovie(): Result<Movie>

    fun getMoviesByCategoryFromDB(category: String): LiveData<List<MovieEntity>>

    suspend fun getMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<MovieEntity>>

}