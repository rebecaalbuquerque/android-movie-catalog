package com.albuquerque.moviecatalog.app.repository

import com.albuquerque.moviecatalog.app.model.ui.MovieUI

interface IRepository {

    suspend fun getPopular(): List<MovieUI>

}