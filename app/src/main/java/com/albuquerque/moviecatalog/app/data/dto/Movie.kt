package com.albuquerque.moviecatalog.app.data.dto

import com.google.gson.annotations.SerializedName

data class Movie(
        val id: Int,

        @SerializedName("original_title")
        val originalTitle: String,

        val title: String,

        val overview: String,

        @SerializedName("release_date")
        val releaseDate: String
)