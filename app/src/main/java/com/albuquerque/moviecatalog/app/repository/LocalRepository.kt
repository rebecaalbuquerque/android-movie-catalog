package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.dao.MovieDao
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity

class LocalRepository(
        private val movieDao: MovieDao
): ILocalRepository {

    override suspend fun saveAll(data: List<MovieEntity>) {
        movieDao.insertAll(data)
    }

    override fun getMovies(): LiveData<List<MovieEntity>> = movieDao.getAll()

    override fun getMoviesByCategory(category: String): LiveData<List<MovieEntity>> = movieDao.getAllByCategory(category)
}