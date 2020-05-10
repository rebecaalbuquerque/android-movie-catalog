package com.albuquerque.moviecatalog.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import com.albuquerque.moviecatalog.app.data.entity.isEqual
import com.albuquerque.moviecatalog.app.utils.TypeMovies

@Dao
interface MovieDao: BaseDao<MovieEntity> {

    @Query("select * from movieentity")
    fun getAll(): List<MovieEntity>

    @Query("select * from movieentity where category = :value order by fetchAt asc")
    fun getAllByCategory(value: String): LiveData<List<MovieEntity>>

    @Query("select * from movieentity where id = 1")
    fun get(): LiveData<MovieEntity?>

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