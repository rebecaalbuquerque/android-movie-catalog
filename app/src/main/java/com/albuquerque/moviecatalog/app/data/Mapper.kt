package com.albuquerque.moviecatalog.app.data

import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.utils.TypeMovies

fun Movie.toEntity(category: TypeMovies): MovieEntity {
    return MovieEntity(
            this.id,
            this.originalTitle,
            this.title,
            this.overview,
            this.releaseDate,
            category.value
    )
}

fun MovieEntity.toUI(): MovieUI {
    return MovieUI(
            this.id,
            this.title,
            this.overview,
            this.releaseDate,
            ""
    )
}

fun Movie.toUI(): MovieUI {
    return MovieUI(
            this.id,
            this.title,
            this.overview,
            this.releaseDate,
            this.poster
    )
}