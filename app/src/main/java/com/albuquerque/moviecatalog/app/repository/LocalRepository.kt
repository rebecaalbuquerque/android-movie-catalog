package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.dao.MovieDao
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import kotlinx.coroutines.flow.Flow

class LocalRepository(
        private val movieDao: MovieDao
): ILocalRepository {

    override suspend fun saveMovies(data: List<MovieEntity>) {
        movieDao.insertAll(data)
    }

    override suspend fun saveMovies(data: List<MovieEntity>, typeMovies: TypeMovies) {
        movieDao.insertAllIfNecesseray(data, typeMovies)
    }

    override fun getMoviesByCategory(category: String): Flow<List<MovieEntity>> = movieDao.getAllByCategory(category)

    override suspend fun updateMovie(movie: MovieEntity) {
        movieDao.update(movie)
    }

    override suspend fun getMovie(movieId: Int): MovieEntity? {
        return movieDao.get(movieId)
    }
}