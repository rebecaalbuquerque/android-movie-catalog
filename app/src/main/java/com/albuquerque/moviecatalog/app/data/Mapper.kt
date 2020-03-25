package com.albuquerque.moviecatalog.app.data

import com.albuquerque.moviecatalog.app.data.dto.Movie
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.ui.MovieUI

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