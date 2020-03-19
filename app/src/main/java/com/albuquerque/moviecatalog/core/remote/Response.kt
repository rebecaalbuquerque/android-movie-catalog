package com.albuquerque.moviecatalog.core.remote

data class Response<T>(
        val page: Int = 0,
        val results: List<T> = arrayListOf(),
        val totalResults: Int = 0,
        val totalPages: Int = 0
)