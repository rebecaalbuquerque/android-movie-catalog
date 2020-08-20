package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Cast
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.remote.MoviesAPI
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Remote
import java.util.*

class RemoteRepository : Remote(), IRemoteRepository {

    private val tmdbApi by lazy { getRetrofitBuilder().create(MoviesAPI::class.java) }

    override suspend fun fetchMovie(movieId: Int): Result<Movie> {
        return runRequest {
            tmdbApi.fetchMovie(movieId)
        }.map {
            it.fetchAt = Calendar.getInstance().time
            it
        }

    }

    override suspend fun fetchMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>> {
        // TODO: corrigir paginação
        /*return if (!paginationController.hasLoadLastPage) {
            val result = when (typeMovies) {
                TypeMovies.POPULAR -> runRequest { tmdbApi.fetchPopular(page) }
                TypeMovies.NOW_PLAYING -> runRequest { tmdbApi.fetchNowPlaying(page) }
                TypeMovies.TOP_RATED -> runRequest { tmdbApi.fetchTopRated(page) }
                TypeMovies.UPCOMING -> runRequest { tmdbApi.fetchUpcoming(page) }
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
            TypeMovies.POPULAR -> runRequest { tmdbApi.fetchPopular(page) }
            TypeMovies.NOW_PLAYING -> runRequest { tmdbApi.fetchNowPlaying(page) }
            TypeMovies.TOP_RATED -> runRequest { tmdbApi.fetchTopRated(page) }
            TypeMovies.UPCOMING -> runRequest { tmdbApi.fetchUpcoming(page) }
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

    override suspend fun fetchCastFromMovie(movieId: Int): Result<List<Cast>> {
        return runRequest { tmdbApi.fetchCastAndCrew(movieId).cast }
    }

    override suspend fun fetchSearch(query: String): Result<List<Movie>> {
        return runRequest { tmdbApi.fetchSearch(query) }.map { list -> list.results.filter { !it.adult } }
    }

}