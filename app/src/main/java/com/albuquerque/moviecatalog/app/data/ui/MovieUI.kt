package com.albuquerque.moviecatalog.app.data.ui

import com.albuquerque.moviecatalog.core.extensions.format
import com.albuquerque.moviecatalog.core.extensions.parse
import java.io.Serializable
import java.util.*

data class MovieUI(
        val id: Int,
        val title: String,
        val overview: String,
        val releaseDate: String,
        val poster: String,
        val backdrop: String,
        val category: String,
        val runtime: String,
        var fetchAt: Date,
        var isFavorite: Boolean
): Serializable {

    val releaseDateFormatted: String = releaseDate.parse("yyyy-MM-dd").format()

}