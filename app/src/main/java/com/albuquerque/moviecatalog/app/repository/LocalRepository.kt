package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.dao.MovieDao
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies

class LocalRepository(
        private val movieDao: MovieDao
): ILocalRepository {

    override suspend fun saveMovies(data: List<MovieEntity>) {
        movieDao.insertAll(data)
    }

    override suspend fun saveMovies(data: List<MovieEntity>, typeMovies: TypeMovies) {
        movieDao.insertAllIfNecesseray(data, typeMovies)
    }

    override fun getMoviesByCategory(category: String): LiveData<List<MovieEntity>> = movieDao.getAllByCategory(category)

    override suspend fun updateMovie(movie: MovieEntity) {
        movieDao.update(movie)
    }

    override fun getMovie(movieId: Int): LiveData<MovieEntity?> {
        return movieDao.get(movieId)
    }
}