package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.model.dto.Movie

class RemoteRepository: IRemoteRepository {

    override suspend fun getPopular(): List<Movie> {
        return emptyList()
    }
}