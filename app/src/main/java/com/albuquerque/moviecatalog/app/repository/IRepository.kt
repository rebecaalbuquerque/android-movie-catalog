package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.entity.CastEntity
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result

interface IRepository {

    fun getMovieFromDB(movieId: Int): LiveData<MovieEntity?>

    suspend fun getMovieDetails(movieUI: MovieUI): Result<MovieEntity>

    fun getMoviesByCategoryFromDB(category: String): LiveData<List<MovieEntity>>

    suspend fun getMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<MovieEntity>>

    suspend fun getCastFromMovie(movieId: Int): Result<List<CastEntity>>

}