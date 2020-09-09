package com.albuquerque.domain.repository

import com.albuquerque.data.dto.Cast
import com.albuquerque.data.dto.Movie
import com.albuquerque.data.entity.MovieEntity
import com.albuquerque.data.toEntity
import com.albuquerque.domain.remote.Pagination
import com.albuquerque.core.util.TypeMovies
import kotlinx.coroutines.flow.Flow

/**
 * @see RemoteRepository
 * @see LocalRepository
 * */

class Repository(
        val remote: IRemoteRepository,
        val local: ILocalRepository
) : IRepository {

    override suspend fun fetchMovieFromAPI(movieId: Int): Result<Movie> {
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

    override suspend fun fetchMoviesFromAPI(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<Movie>> {

        return remote
                .fetchMoviesPaginatedByCategory(paginationController, page, typeMovies)
                .onSuccess { result ->
                    val moviesEntity = result.map { it.toEntity(typeMovies).apply { restoreFromDB() } }
                    local.saveMovies(moviesEntity, typeMovies)
                }
    }

    override fun getMoviesFromDB(category: String): Flow<List<MovieEntity>> = local.getMoviesByCategory(category)

    override suspend fun fetchCastMovieFromAPI(movieId: Int): Result<List<Cast>> {
        return remote.fetchCastFromMovie(movieId)
    }

    override suspend fun fetchSearchFromAPI(query: String): Result<List<Movie>> {
        return remote.fetchSearch(query)
    }

    override suspend fun handleFavorite(movieId: Int) {
        local.getMovie(movieId)?.apply {
            this.isFavorite = !this.isFavorite
        }?.let {
            local.updateMovie(it)
        }
    }

    override fun getFavoritesFromDB(): Flow<List<MovieEntity>> = local.getFavorites()

    private suspend fun MovieEntity.restoreFromDB() {
        local.getMovie(this.id)?.let { movie ->
            this.fetchAt = movie.fetchAt
            this.category = movie.category
            this.isFavorite = movie.isFavorite
        }
    }
}