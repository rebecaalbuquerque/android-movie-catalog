package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.model.dto.Movie
import com.albuquerque.moviecatalog.app.remote.MoviesAPI
import com.albuquerque.moviecatalog.core.remote.IPaginationController
import com.albuquerque.moviecatalog.core.remote.Remote
import com.albuquerque.moviecatalog.core.remote.Result

class RemoteRepository: Remote(), IRemoteRepository {

    private val API by lazy { getRetrofitBuilder().create(MoviesAPI::class.java) }

    override suspend fun getPopular(paginationController: IPaginationController, page: Int): Result<List<Movie>> {
        val result = runRequest(API.fetchPopular(page))

        return if(result is Result.Success) {
            paginationController.totalPages = result.data.totalPages
            paginationController.nextPage = result.data.page + 1

            Result.Success(result.data.results)
        } else {
            Result.Error((result as Result.Error).error)
        }

    }

    override suspend fun getMovie(): Result<Movie> {
        val result = runRequest(API.fetchMovie())

        return if(result is Result.Success) {
            Result.Success(result.data)
        } else {
            Result.Error((result as Result.Error).error)
        }
    }

}