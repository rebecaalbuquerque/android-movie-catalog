package com.albuquerque.moviecatalog.app.remote

import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.dto.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/popular?api_key=${Credentials.API_KEY}")
    suspend fun fetchPopular(
            @Query("page") page: Int = 1
    ): Movies

    @GET("movie/24428?api_key=${Credentials.API_KEY}")
    suspend fun fetchMovie(): Movie

}