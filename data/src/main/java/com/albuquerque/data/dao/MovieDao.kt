package com.albuquerque.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.albuquerque.core.util.TypeMovies
import com.albuquerque.data.entity.MovieEntity
import com.albuquerque.data.entity.isEqual
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao: BaseDao<MovieEntity> {

    @Query("select * from movieentity")
    fun getAll(): List<MovieEntity>

    @Query("select * from movieentity where category = :value order by fetchAt asc")
    fun getAllByCategory(value: String): Flow<List<MovieEntity>>

    @Query("select * from movieentity where isFavorite = 1")
    fun getFavorites(): Flow<List<MovieEntity>>

    @Query("select * from movieentity where id = :movieId")
    fun getAsFlow(movieId: Int): Flow<MovieEntity>

    @Query("select * from movieentity where id = :movieId")
    suspend fun get(movieId: Int): MovieEntity?

    @Query("delete from movieentity")
    fun dropTable()

    @Transaction
    suspend fun insertAllIfNecesseray(list: List<MovieEntity>, typeMovies: TypeMovies) {
        val db = getAll().filter { it.category == typeMovies.value }
        val moviesToSaveOrUpdate = mutableListOf<MovieEntity>()

        list.forEach {  movieAPI ->
            val movieDB = db.firstOrNull { it.id == movieAPI.id }

            movieDB?.let {
                if(!it.isEqual(movieAPI))
                    moviesToSaveOrUpdate.add(movieAPI)
            } ?: kotlin.run {
                moviesToSaveOrUpdate.add(movieAPI)
            }

        }

        insertAll(moviesToSaveOrUpdate)

    }

}