package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result

interface IRepository {

    suspend fun getMovie(): Result<Movie>

    fun getMoviesFromDB(category: String): LiveData<List<MovieEntity>>

    suspend fun getPopular(paginationController: Pagination?, page: Int): Result<List<MovieEntity>>

    suspend fun getNowPlaying(paginationController: Pagination?, page: Int): Result<List<MovieEntity>>

    suspend fun getTopRated(paginationController: Pagination?, page: Int): Result<List<MovieEntity>>

    suspend fun getUpcoming(paginationController: Pagination?, page: Int): Result<List<MovieEntity>>

}