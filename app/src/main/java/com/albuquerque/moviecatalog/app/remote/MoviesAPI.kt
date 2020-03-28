package com.albuquerque.moviecatalog.app.remote

import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.dto.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/24428?api_key=${Credentials.API_KEY}")
    suspend fun fetchMovie(): Movie

    @GET("movie/now_playing?api_key=${Credentials.API_KEY}")
    suspend fun fetchNowPlaying(
            @Query("page") page: Int = 1
    ): Movies

    @GET("movie/popular?api_key=${Credentials.API_KEY}")
    suspend fun fetchPopular(
            @Query("page") page: Int = 1
    ): Movies

    @GET("movie/top_rated?api_key=${Credentials.API_KEY}")
    suspend fun fetchTopRated(
            @Query("page") page: Int = 1
    ): Movies

    @GET("movie/latest?api_key=${Credentials.API_KEY}")
    suspend fun fetchLatest(
            @Query("page") page: Int = 1
    ): Movies

}