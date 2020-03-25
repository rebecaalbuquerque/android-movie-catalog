package com.albuquerque.moviecatalog.app.data.dao

import androidx.lifecycle.LiveData
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class MovieDao: BaseDao<MovieEntity> {

    @Query("select * from movie")
    abstract fun getAll(): LiveData<List<MovieEntity>>

    @Query("select * from movie where id = 1")
    abstract fun get(): LiveData<MovieEntity?>

    @Query("delete from movie")
    abstract fun dropTable()
}