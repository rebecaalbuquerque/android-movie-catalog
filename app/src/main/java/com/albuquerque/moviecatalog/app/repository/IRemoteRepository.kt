package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.model.dto.Movie
import com.albuquerque.moviecatalog.core.remote.IPaginationController
import com.albuquerque.moviecatalog.core.remote.Result

interface IRemoteRepository {

    suspend fun getPopular(paginationController: IPaginationController, page: Int): Result<List<Movie>>

    suspend fun getMovie(): Result<Movie>

}