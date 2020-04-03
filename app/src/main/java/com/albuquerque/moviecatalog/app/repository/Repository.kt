package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.toEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies.*
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result

class Repository(
        val remote: IRemoteRepository,
        val local: ILocalRepository
): IRepository {

    override suspend fun getMovie(): Result<Movie> {
        return remote.getMovie()
    }

    override fun getMoviesFromDB(category: String): LiveData<List<MovieEntity>> = local.getMoviesByCategory(category)

    override suspend fun getPopular(paginationController: Pagination?, page: Int): Result<List<MovieEntity>> {

        return when(val result = remote.getPopular(paginationController, page)) {

            is Result.Success -> {
                val moviesEntity = result.data.map { it.toEntity(POPULAR) }
                local.saveAll(moviesEntity)
                Result.Success(moviesEntity)
            }

            is Result.Error -> {
                Result.Error(result.error)
            }
        }

    }

    override suspend fun getNowPlaying(paginationController: Pagination?, page: Int): Result<List<MovieEntity>> {

        return when(val result = remote.getNowPlaying(paginationController, page)) {

            is Result.Success -> {
                val moviesEntity = result.data.map { it.toEntity(NOW_PLAYING) }
                local.saveAll(moviesEntity)
                Result.Success(moviesEntity)
            }

            is Result.Error -> {
                Result.Error(result.error)
            }
        }
    }

    override suspend fun getTopRated(paginationController: Pagination?, page: Int): Result<List<MovieEntity>> {
        return when(val result = remote.getTopRated(paginationController, page)) {

            is Result.Success -> {
                val moviesEntity = result.data.map { it.toEntity(TOP_RATED) }
                local.saveAll(moviesEntity)
                Result.Success(moviesEntity)
            }

            is Result.Error -> {
                Result.Error(result.error)
            }
        }
    }

    override suspend fun getUpcoming(paginationController: Pagination?, page: Int): Result<List<MovieEntity>> {
        return when(val result = remote.getUpcoming(paginationController, page)) {

            is Result.Success -> {
                val moviesEntity = result.data.map { it.toEntity(UPCOMING) }
                local.saveAll(moviesEntity)
                Result.Success(moviesEntity)
            }

            is Result.Error -> {
                Result.Error(result.error)
            }
        }
    }
}