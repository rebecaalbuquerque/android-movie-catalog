package com.albuquerque.moviecatalog.app.data.dto

data class CastList(
        val id: Int,
        val cast: List<Cast> = arrayListOf()
)