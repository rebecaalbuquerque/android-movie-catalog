package com.albuquerque.moviecatalog.app.remote

import com.albuquerque.moviecatalog.app.data.dto.CastList
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.dto.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/{movieId}?api_key=${Credentials.API_KEY}")
    suspend fun fetchMovie(
            @Path("movieId") movieId: Int,
            @Query("language") language: String = "pt-BR"
    ): Movie

    @GET("movie/now_playing?api_key=${Credentials.API_KEY}")
    suspend fun fetchNowPlaying(
            @Query("page") page: Int = 1,
            @Query("language") language: String = "pt-BR"
    ): Movies

    @GET("movie/popular?api_key=${Credentials.API_KEY}")
    suspend fun fetchPopular(
            @Query("page") page: Int = 1,
            @Query("language") language: String = "pt-BR"
    ): Movies

    @GET("movie/top_rated?api_key=${Credentials.API_KEY}")
    suspend fun fetchTopRated(
            @Query("page") page: Int = 1,
            @Query("language") language: String = "pt-BR"
    ): Movies

    @GET("movie/upcoming?api_key=${Credentials.API_KEY}")
    suspend fun fetchUpcoming(
            @Query("page") page: Int = 1,
            @Query("language") language: String = "pt-BR"
    ): Movies

    @GET("/movie/{movie_id}/credits?api_key=${Credentials.API_KEY}")
    suspend fun fetchCastAndCrew(
            @Path("movie_id") movieId: Int,
            @Query("language") language: String = "pt-BR"
    ): CastList

}