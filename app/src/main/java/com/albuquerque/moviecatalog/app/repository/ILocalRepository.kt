package com.albuquerque.moviecatalog.app.repository

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.entity.CastEntity
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity

interface ILocalRepository {

    suspend fun saveMovies(data: List<MovieEntity>)

    fun getMovies(): LiveData<List<MovieEntity>>

    fun getMoviesByCategory(category: String): LiveData<List<MovieEntity>>

}