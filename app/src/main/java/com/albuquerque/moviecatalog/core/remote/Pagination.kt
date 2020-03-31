package com.albuquerque.moviecatalog.core.remote

open class Pagination(
        private var totalPages: Int,
        var nextPage: Int
) {

    open fun getNext(refresh: Boolean) {

    }

    fun updatePages(current: Int, totalPages: Int) {
        this.totalPages = totalPages

        if (current.plus(1) <= totalPages) {
            nextPage = nextPage.plus(1)
        }
    }

}