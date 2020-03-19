package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.model.ui.MovieUI

class Repository(
        val remote: IRemoteRepository
): IRepository {

    override suspend fun getPopular(): List<MovieUI> {
        return emptyList()
    }

}