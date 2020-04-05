package com.albuquerque.moviecatalog.app.data

import com.albuquerque.moviecatalog.app.data.dto.Cast
import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.CastEntity
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.ui.CastUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.utils.TypeMovies

/*
* Movie
* */
fun Movie.toEntity(category: TypeMovies): MovieEntity {
    return MovieEntity(
            this.id,
            this.originalTitle,
            this.title,
            this.overview,
            this.releaseDate,
            this.poster,
            category.value
    )
}

fun MovieEntity.toUI(): MovieUI {
    return MovieUI(
            this.id,
            this.title,
            this.overview,
            this.releaseDate,
            this.poster,
            this.category
    )
}

/*
* Cast
* */

fun CastEntity.toUI(): CastUI {
    return CastUI(
            this.id,
            this.character,
            this.gender,
            this.order,
            this.name,
            this.profilePath
    )
}

fun Cast.toEntity(): CastEntity {
    return CastEntity(
            this.id,
            this.character,
            this.gender ?: "N/I",
            this.order,
            this.name,
            this.profilePath
    )
}