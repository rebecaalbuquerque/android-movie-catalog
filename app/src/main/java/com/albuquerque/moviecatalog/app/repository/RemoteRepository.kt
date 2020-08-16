package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Cast
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.remote.MoviesAPI
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Remote
import java.util.*

class RemoteRepository : Remote(), IRemoteRepository {

    private val API by lazy { getRetrofitBuilder().create(MoviesAPI::class.java) }

    override suspend fun getMovie(movieId: Int, fetchAt: Date): Result<Movie> {
        val result = runRequest { API.fetchMovie(movieId) }

        if(result.isSuccess) {
            result.map {
                it.fetchAt = fetchAt
            }
        }

        return result

    }

    override suspend fun getMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>> {
        // TODO: corrigir paginação
        /*return if (!paginationController.hasLoadLastPage) {
            val result = when (typeMovies) {
                TypeMovies.POPULAR -> runRequest { API.fetchPopular(page) }
                TypeMovies.NOW_PLAYING -> runRequest { API.fetchNowPlaying(page) }
                TypeMovies.TOP_RATED -> runRequest { API.fetchTopRated(page) }
                TypeMovies.UPCOMING -> runRequest { API.fetchUpcoming(page) }
            }.onSuccess { movies ->
                paginationController.updatePages(page, movies.totalPages, movies.totalResults)
            }

            return Result.success(
                    result.getOrNull()?.let { movies ->
                        movies.results.forEach { it.fetchAt = Calendar.getInstance().time }
                        movies.results.filter { !it.adult }
                    } ?: emptyList()
            )

        } else {
            Result.success(listOf())
        }*/

        val result = when (typeMovies) {
            TypeMovies.POPULAR -> runRequest { API.fetchPopular(page) }
            TypeMovies.NOW_PLAYING -> runRequest { API.fetchNowPlaying(page) }
            TypeMovies.TOP_RATED -> runRequest { API.fetchTopRated(page) }
            TypeMovies.UPCOMING -> runRequest { API.fetchUpcoming(page) }
        }.onSuccess { movies ->
            paginationController.updatePages(page, movies.totalPages, movies.totalResults)
        }

        if(result.isSuccess) {
            result.map {movies ->
                movies.results.forEach { it.fetchAt = Calendar.getInstance().time }
                movies.results.filter { !it.adult }
                movies.results
            }
        }

        return result.map { it.results }

    }

    override suspend fun getCastFromMovie(movieId: Int): Result<List<Cast>> {
        return runRequest { API.fetchCastAndCrew(movieId).cast }
    }
}