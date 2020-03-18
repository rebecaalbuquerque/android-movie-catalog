package com.albuquerque.moviecatalog

import com.facebook.stetho.okhttp3.StethoInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class Remote: CoroutineScope by CoroutineScope(Dispatchers.IO) {

    companion object {

        val okHttpClient: OkHttpClient = let {
            val loggingLevel = HttpLoggingInterceptor.Level.BODY

            OkHttpClient.Builder().apply {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = loggingLevel

                addInterceptor(MoviesInterceptor())
                addInterceptor(loggingInterceptor)

                addNetworkInterceptor(StethoInterceptor())

            }.build()

        }

    }

    fun getRetrofitBuilder(url: String = Config.BASE_URL): Retrofit {

        return Retrofit.Builder().apply {

            baseUrl(url)
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)

        }.build()

    }

}