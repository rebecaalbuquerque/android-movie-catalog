package com.albuquerque.moviecatalog.core.remote

interface IPaginationController {

    var totalPages: Int
    var nextPage: Int

    fun getNext(refresh: Boolean = false)

}