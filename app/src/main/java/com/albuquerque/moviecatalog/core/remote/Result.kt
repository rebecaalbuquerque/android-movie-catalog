package com.albuquerque.moviecatalog.core.remote

import java.lang.Exception

sealed class Result<T> {

    class Success<T>(val data: T): Result<T>()
    class Error<T>(val error: Exception): Result<T>()

}