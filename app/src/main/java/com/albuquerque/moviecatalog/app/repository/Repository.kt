package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result

class Repository(
        val remote: IRemoteRepository
): IRepository {

    override suspend fun getMovie(): Result<Movie> {
        return remote.getMovie()
    }

    override suspend fun getPopular(paginationController: Pagination?, page: Int): Result<List<Movie>> {
        return remote.getPopular(paginationController, page)
    }

    override suspend fun getNowPlaying(paginationController: Pagination?, page: Int): Result<List<Movie>> {
        return remote.getNowPlaying(paginationController, page)
    }

    override suspend fun getTopRated(paginationController: Pagination?, page: Int): Result<List<Movie>> {
        return remote.getTopRated(paginationController, page)
    }

    override suspend fun getUpcoming(paginationController: Pagination?, page: Int): Result<List<Movie>> {
        return remote.getUpcoming(paginationController, page)
    }
}