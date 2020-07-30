package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Cast
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.remote.MoviesAPI
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Remote
import com.albuquerque.moviecatalog.core.remote.Result
import java.util.*

class RemoteRepository: Remote(), IRemoteRepository {

    private val API by lazy { getRetrofitBuilder().create(MoviesAPI::class.java) }

    override suspend fun getMovie(): Result<Movie> {
        val result = runRequest(API.fetchMovie())

        return if(result is Result.Success) {
            Result.Success(result.data)
        } else {
            Result.Error((result as Result.Error).error)
        }
    }

    override suspend fun getMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>> {

        return if(!paginationController.hasLoadLastPage) {
            val result = when(typeMovies) {
                TypeMovies.POPULAR -> runRequest(API.fetchPopular(page))
                TypeMovies.NOW_PLAYING -> runRequest(API.fetchNowPlaying(page))
                TypeMovies.TOP_RATED -> runRequest(API.fetchTopRated(page))
                TypeMovies.UPCOMING -> runRequest(API.fetchUpcoming(page))
            }

            return if(result is Result.Success) {
                val data = result.data

                paginationController.updatePages(page, data.totalPages, data.totalResults)

                data.results.forEach { it.fetchAt = Calendar.getInstance().time }

                Result.Success(data.results.filter { !it.adult })

            } else {
                Result.Error((result as Result.Error).error)
            }

        } else {
            Result.Success(listOf())
        }

    }

    override suspend fun getCastFromMovie(movieId: Int): Result<List<Cast>> {
        val result = runRequest(API.fetchCastAndCrew(movieId))

        return if(result is Result.Success) {
            Result.Success(result.data.cast)
        } else {
            Result.Error((result as Result.Error).error)
        }
    }
}