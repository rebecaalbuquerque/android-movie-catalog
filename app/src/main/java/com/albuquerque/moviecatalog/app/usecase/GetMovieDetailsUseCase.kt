package com.albuquerque.moviecatalog.app.usecase

import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieDetailsUseCase(
        private val repository: IRepository
): CoroutineScope by CoroutineScope(Dispatchers.IO) {

    suspend fun invoke(movie: MovieUI): Result<MovieUI> {

        return withContext(coroutineContext) {

            when (val result = repository.getMovieDetails(movie)) {
                is Result.Success<MovieEntity> -> {
                    Result.Success(result.data.toUI() )
                }
                is Result.Error<MovieEntity> -> {
                    //result.error.errorMessage = StringWrapper("Não foi possível avaliar o artigo. Verifique sua conexão com a internet")
                    Result.Error<MovieUI>(result.error)
                }
            }

        }
    }

}