package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.data.dto.Cast
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import kotlinx.coroutines.flow.Flow

/**
 * @see RemoteRepository
 * @see LocalRepository
 * */

class Repository(
        val remote: IRemoteRepository,
        val local: ILocalRepository
) : IRepository {

    override suspend fun fetchMovieDetails(movieId: Int): Result<Movie> {
        var category = ""

        val result = remote.fetchMovie(movieId)
                .onSuccess { result ->
                    val movieEntity = result
                            .toEntity()
                            .apply { restoreFromDB() }

                    category = movieEntity.category

                    local.updateMovie(movieEntity)
                }

        if(result.isSuccess) {
            result.map { it.category = category }
        }

        return result

    }

    override fun getMovieFromDB(movieId: Int): Flow<MovieEntity> = local.getMovieAsFlow(movieId)

    override suspend fun fetchMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>> {

        return remote.fetchMoviesPaginatedByCategory(paginationController, page, typeMovies)
                .onSuccess { result ->
                    val moviesEntity = result.map { it.toEntity(typeMovies) }
                    local.saveMovies(moviesEntity, typeMovies)
                }
    }

    override fun getMoviesByCategoryFromDB(category: String): Flow<List<MovieEntity>> = local.getMoviesByCategory(category)

    override suspend fun getCastFromMovie(movieId: Int): Result<List<Cast>> {

        return remote.fetchCastFromMovie(movieId)
                .onSuccess { result ->
                    val moviesEntity = result.map { it.toEntity() }
                    //local.saveAll(moviesEntity)
                }
    }

    private suspend fun MovieEntity.restoreFromDB() {
        local.getMovie(this.id)?.let { movie ->
            this.fetchAt = movie.fetchAt
            this.category = movie.category
        }
    }
}