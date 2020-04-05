package com.albuquerque.moviecatalog.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.albuquerque.moviecatalog.app.data.entity.CastEntity

abstract class CastDao: BaseDao<CastEntity> {

    @Query("select * from movieentity where category = :value")
    abstract fun getCastByMovie(value: String): LiveData<List<CastEntity>>

}