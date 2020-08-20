package com.albuquerque.moviecatalog.app.data.dto

import com.albuquerque.moviecatalog.app.remote.Config
import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie (
        val id: Int,

        @SerializedName("original_title")
        val originalTitle: String,

        val title: String,

        val overview: String,

        @SerializedName("release_date")
        val releaseDate: String,

        @SerializedName("poster_path")
        val posterPath: String,

        @SerializedName("backdrop_path")
        val backdropPath: String,

        val adult: Boolean,

        val runtime: Int
) {
    val poster: String
        get() = Config.BASE_IMAGE_URL.plus(posterPath)

    val backdrop: String
        get() = Config.BASE_IMAGE_URL.plus(backdropPath)

    var fetchAt: Date = Calendar.getInstance().time

    var category: String = ""

}