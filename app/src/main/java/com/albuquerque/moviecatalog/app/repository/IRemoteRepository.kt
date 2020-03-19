package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.model.dto.Movie

interface IRemoteRepository {
    suspend fun getPopular(): List<Movie>
}