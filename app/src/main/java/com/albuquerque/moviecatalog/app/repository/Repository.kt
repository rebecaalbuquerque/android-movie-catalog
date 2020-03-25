package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.core.remote.IPaginationController
import com.albuquerque.moviecatalog.core.remote.Result

class Repository(
        val remote: IRemoteRepository
): IRepository {

    override suspend fun getPopular(paginationController: IPaginationController, page: Int): Result<List<Movie>> {
        return remote.getPopular(paginationController, page)
    }

    override suspend fun getMovie(): Result<Movie> {
        return remote.getMovie()
    }
}