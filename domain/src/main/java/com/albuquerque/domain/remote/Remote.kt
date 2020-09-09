package com.albuquerque.domain.remote

import android.util.Log
import com.albuquerque.core.util.Config
import com.facebook.stetho.okhttp3.StethoInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException

abstract class Remote {

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

    suspend fun <T> runRequest(request: suspend () -> T): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(request.invoke())
            } catch (throwable: Throwable) {
                Log.d("runRequest() error", throwable.message ?: "")
                when (throwable) {
                    is HttpException -> {

                        /*
                        * Caso a API retornasse um objeto no erro, aqui poderia ser feito uma
                        * verificação para checar se tem o objeto fornecido pela API, se não tiver
                        * exibe os erros genéricos contidos no "when" abaixo
                        * */

                        when (throwable.code()) {
                            HttpURLConnection.HTTP_BAD_REQUEST -> Result.failure(Throwable("Houve um problema ao tentar processar esta requisição."))
                            HttpURLConnection.HTTP_UNAUTHORIZED -> Result.failure(Throwable("Usuário não autorizado para realizar esta requisição."))
                            HttpURLConnection.HTTP_NOT_FOUND -> Result.failure(Throwable("Informações solicitadas não encontradas"))
                            in 500..599 -> Result.failure(Throwable("Serviço indisponível. Tente novamente mais tarde"))
                            else -> Result.failure(Throwable("Ocorreu um erro inesperado, tente novamente."))
                        }
                    }
                    is UnknownHostException -> Result.failure(Throwable("Não foi possível realizar uma conexão. Verifique sua conexão com a internet e tente novamente."))
                    is ConnectException -> Result.failure(Throwable("Não foi possível realizar uma conexão. Verifique sua conexão com a internet e tente novamente."))
                    else -> Result.failure<T>(Throwable("Ocorreu um erro inesperado, tente novamente."))
                }
            }
        }
    }

    /*fun <T> runRequest(request: T): Result<T> {

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

    }*/

}