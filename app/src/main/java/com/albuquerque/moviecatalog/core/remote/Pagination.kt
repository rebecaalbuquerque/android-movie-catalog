package com.albuquerque.moviecatalog.core.remote

open class Pagination(
        var nextPage: Int
) {

    private var totalPages: Int = 0
    private var totalItems: Int = 0

    var hasLoadLastPage = false

    fun hasReachedEndPagination(totalItems: Int) {
        if(this.totalItems == totalItems) {
            this.hasLoadLastPage = true
            this.nextPage = this.totalPages
        }
    }

    fun updatePages(current: Int, totalPages: Int, totalItems: Int) {
        this.totalPages = totalPages
        this.totalItems = totalItems

        if (current.plus(1) <= totalPages) {
            nextPage = nextPage.plus(1)
        }

        if(current == totalPages && !hasLoadLastPage)
            hasLoadLastPage = true

    }

}