package com.albuquerque.moviecatalog.app.remote

import com.albuquerque.moviecatalog.app.model.dto.Movie
import com.albuquerque.moviecatalog.core.remote.Response
import retrofit2.http.GET

interface MoviesAPI {

    @GET("movie/popular")
    suspend fun fetchPopular(): Response<Movie>

}