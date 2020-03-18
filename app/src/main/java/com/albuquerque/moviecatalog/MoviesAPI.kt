package com.albuquerque.moviecatalog

import retrofit2.http.GET

interface MoviesAPI {

    @GET("movie/popular")
    suspend fun fetchPopular(): Response<Movie>

}