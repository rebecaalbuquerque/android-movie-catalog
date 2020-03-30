package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.core.remote.IPaginationController
import com.albuquerque.moviecatalog.core.remote.Result

interface IRemoteRepository {

    suspend fun getMovie(): Result<Movie>

    suspend fun getPopular(paginationController: IPaginationController?, page: Int): Result<List<Movie>>

    suspend fun getNowPlaying(paginationController: IPaginationController?, page: Int): Result<List<Movie>>

    suspend fun getTopRated(paginationController: IPaginationController?, page: Int): Result<List<Movie>>

    suspend fun getUpcoming(paginationController: IPaginationController?, page: Int): Result<List<Movie>>

}