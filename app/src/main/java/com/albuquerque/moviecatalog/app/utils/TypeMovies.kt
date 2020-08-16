package com.albuquerque.moviecatalog.app.utils

enum class TypeMovies(val value: String) {
    POPULAR("Populares"),
    NOW_PLAYING("Em Exibição"),
    TOP_RATED("Melhores Avaliados"),
    UPCOMING("Em Breve");

    companion object {
        fun getByValue(s: String): TypeMovies {
            return values().find { it.value == s } ?: POPULAR
        }
    }

}