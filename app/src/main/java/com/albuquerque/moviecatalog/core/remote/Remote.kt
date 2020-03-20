package com.albuquerque.moviecatalog.core.remote

import com.albuquerque.moviecatalog.app.remote.Config
import com.albuquerque.moviecatalog.app.remote.MoviesInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

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

        const val FIRST_PAGE_PAGINATION = 1

    }

    fun getRetrofitBuilder(url: String = Config.BASE_URL): Retrofit {

        return Retrofit.Builder().apply {

            baseUrl(url)
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)

        }.build()

    }

    fun <T> runRequest(request: T): Result<T> {

        try {

            return Result.Success(request)

        } catch (e: Exception) {

            (e as? HttpException)?.response()?.errorBody()?.let { response ->
                return try {
                    val message = JSONObject(response.toString()).getString("status_message")
                    Result.Error(Exception(message, e.cause))
                } catch (e: Exception) {
                    Result.Error(e)
                }
            } ?: kotlin.run {
                return Result.Error(e)
            }

        }

    }

}