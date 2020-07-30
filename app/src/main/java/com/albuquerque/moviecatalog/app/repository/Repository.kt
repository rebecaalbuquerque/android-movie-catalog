package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.CastEntity
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result

/**
 * @see RemoteRepository
 * @see LocalRepository
 * */

class Repository(
        val remote: IRemoteRepository,
        val local: ILocalRepository
): IRepository {

    override suspend fun getMovie(): Result<Movie> {
        return remote.getMovie()
    }

    override suspend fun getMoviesPaginatedByCategory(paginationController: Pagination, page: Int, typeMovies: TypeMovies): Result<List<MovieEntity>> {
        return when(val result = remote.getMoviesPaginatedByCategory(paginationController, page, typeMovies)) {

            is Result.Success -> {
                val moviesEntity = result.data.map { it.toEntity(typeMovies) }
                local.saveMovies(moviesEntity, typeMovies)
                Result.Success(moviesEntity)
            }

            is Result.Error -> {
                Result.Error(result.error)
            }
        }
    }

    override fun getMoviesByCategoryFromDB(category: String): LiveData<List<MovieEntity>> = local.getMoviesByCategory(category)

    override suspend fun getCastFromMovie(movieId: Int): Result<List<CastEntity>> {
        return when(val result = remote.getCastFromMovie(movieId)) {

            is Result.Success -> {
                val moviesEntity = result.data.map { it.toEntity() }
                //local.saveAll(moviesEntity)
                Result.Success(moviesEntity)
            }

            is Result.Error -> {
                Result.Error(result.error)
            }
        }
    }
}