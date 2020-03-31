package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result

interface IRemoteRepository {

    suspend fun getMovie(): Result<Movie>

    suspend fun getPopular(paginationController: Pagination?, page: Int): Result<List<Movie>>

    suspend fun getNowPlaying(paginationController: Pagination?, page: Int): Result<List<Movie>>

    suspend fun getTopRated(paginationController: Pagination?, page: Int): Result<List<Movie>>

    suspend fun getUpcoming(paginationController: Pagination?, page: Int): Result<List<Movie>>

}