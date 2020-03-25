package com.albuquerque.moviecatalog.app.data.dto

import com.google.gson.annotations.SerializedName

data class Movies(
        val page: Int = 0,

        val results: List<Movie> = arrayListOf(),

        val totalResults: Int = 0,

        val totalPages: Int = 0,

        @SerializedName("status_code")
        val statusCode: String? = null,

        @SerializedName("status_message")
        val statusMessage: String? = null
)