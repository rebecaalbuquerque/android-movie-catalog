package com.albuquerque.moviecatalog.app.usecase

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.toUI
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.app.utils.TypeMovies
import com.albuquerque.moviecatalog.core.remote.Pagination
import com.albuquerque.moviecatalog.core.remote.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class GetPopularUseCase(
        private val repository: IRepository
) {

    suspend fun invoke(page: Int, paginationController: Pagination? = null): Flow<LiveData<List<MovieEntity>>> = flow {

        emit(
                repository.getMoviesFromDB(TypeMovies.POPULAR.value)
        )

        val result = withContext(coroutineContext) {
            repository.getPopular(paginationController, page)
        }

        if(result is Result.Success) {
            Result.Success(result.data.map { it.toUI() })
        } else {
            throw (result as Result.Error).error
        }

    }

}