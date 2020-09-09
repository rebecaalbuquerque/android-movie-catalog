package com.albuquerque.data

import com.albuquerque.core.util.TypeMovies
import com.albuquerque.data.dto.Cast
import com.albuquerque.data.dto.Movie
import com.albuquerque.data.entity.CastEntity
import com.albuquerque.data.entity.MovieEntity
import com.albuquerque.data.ui.CastUI
import com.albuquerque.data.ui.MovieUI
import java.util.*

/*
* Movie
* */
fun Movie.toUI(): MovieUI {
    // todo: melhorar depois
    return MovieUI(
            this.id,
            this.title,
            if (this.overview.isEmpty()) "N/A" else this.overview,
            this.releaseDate,
            this.poster,
            this.backdrop,
            "",
            "${this.runtime} min",
            Calendar.getInstance().time,
            false
    )
}

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
            this.fetchAt,
            this.isFavorite
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