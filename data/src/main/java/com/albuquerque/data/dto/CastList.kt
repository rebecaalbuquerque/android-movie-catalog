package com.albuquerque.data.dto

data class CastList(
        val id: Int,
        val cast: List<Cast> = arrayListOf()
)