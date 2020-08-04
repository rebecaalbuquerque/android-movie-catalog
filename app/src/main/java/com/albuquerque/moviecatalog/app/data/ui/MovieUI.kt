package com.albuquerque.moviecatalog.app.data.ui

import java.io.Serializable

data class MovieUI(
        val id: Int,
        val title: String,
        val overview: String,
        val releaseDate: String,
        val poster: String,
        val backdrop: String,
        val category: String
): Serializable