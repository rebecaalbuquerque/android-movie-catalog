package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Cast
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * @see RemoteRepository
 * @see LocalRepository
 * */

class Repository(
        val remote: IRemoteRepository,
        val local: ILocalRepository
) : IRepository {

    override suspend fun getMovieDetails(movieId: Int, movieCategory: String, fetchAt: Date): Result<Movie> {

        return remote.getMovie(movieId, fetchAt)
                .onSuccess { result ->
                    val movieEntity = result.toEntity(TypeMovies.getByValue(movieCategory)).apply { restoreFromDB() }
                    local.updateMovie(movieEntity)
                }

    }

    override suspend fun getMovieFromDB(movieId: Int): MovieEntity? = local.getMovie(movieId)

    override suspend fun getMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>> {

        return remote.getMoviesPaginatedByCategory(paginationController, page, typeMovies)
                .onSuccess { result ->
                    val moviesEntity = result.map { it.toEntity(typeMovies) }
                    local.saveMovies(moviesEntity, typeMovies)
                }
    }

    override fun getMoviesByCategoryFromDB(category: String): Flow<List<MovieEntity>> = local.getMoviesByCategory(category)

    override suspend fun getCastFromMovie(movieId: Int): Result<List<Cast>> {

        return remote.getCastFromMovie(movieId)
                .onSuccess { result ->
                    val moviesEntity = result.map { it.toEntity() }
                    //local.saveAll(moviesEntity)
                }
    }

    private suspend fun MovieEntity.restoreFromDB() {
        local.getMovie(this.id)?.let { movie ->
            this.fetchAt = movie.fetchAt
        }
    }
}