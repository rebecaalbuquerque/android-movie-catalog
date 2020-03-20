package com.albuquerque.moviecatalog.app.model

import com.albuquerque.moviecatalog.app.model.dto.Movie
import com.albuquerque.moviecatalog.app.model.entity.MovieEntity
import com.albuquerque.moviecatalog.app.model.ui.MovieUI

fun Movie.toEntity(): MovieEntity {
    return null!!
}

fun MovieEntity.toUI(): MovieUI {
    return null!!
}

fun Movie.toUI(): MovieUI {
    return MovieUI(
            this.id,
            this.title
    )
}