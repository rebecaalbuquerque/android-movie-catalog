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
fun Movie.toEntity(category: TypeMovies? = null): MovieEntity {
    return MovieEntity(
            this.id,
            this.originalTitle,
            this.title,
            if(this.overview.isEmpty()) "N/A" else this.overview,
            this.releaseDate,
            this.poster,
            this.backdrop,
            category?.value ?: "",
            "${this.runtime} min",
            this.fetchAt
    )
}

fun MovieEntity.toUI(): MovieUI {
    return MovieUI(
            this.id,
            this.title,
            this.overview,
            this.releaseDate,
            this.poster,
            this.backdrop,
            this.category,
            this.runtime,
            this.fetchAt
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
            this.name,
            this.profilePicture
    )
}

fun Cast.toEntity(): CastEntity {
    return CastEntity(
            this.id,
            this.character,
            this.gender ?: "N/I",
            this.order,
            this.name,
            this.profilePicture
    )
}