package com.albuquerque.domain.remote

import okhttp3.Interceptor
import okhttp3.Response

class MoviesInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .header("Content-Type", "application/json")
                .build()

        return chain.proceed(request)
    }
}