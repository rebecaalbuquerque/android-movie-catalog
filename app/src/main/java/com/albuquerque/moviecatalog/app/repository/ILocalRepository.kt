package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.utils.TypeMovies

interface ILocalRepository {

    suspend fun saveMovies(data: List<MovieEntity>)

    suspend fun saveMovies(data: List<MovieEntity>, typeMovies: TypeMovies)

    suspend fun updateMovie(movie: MovieEntity)

    fun getMoviesByCategory(category: String): LiveData<List<MovieEntity>>

    fun getMovie(movieId: Int): LiveData<MovieEntity?>

}