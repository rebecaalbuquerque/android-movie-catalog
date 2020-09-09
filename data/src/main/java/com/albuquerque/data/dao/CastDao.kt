package com.albuquerque.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.albuquerque.data.entity.CastEntity

interface CastDao: BaseDao<CastEntity> {

    @Query("select * from movieentity where category = :value")
    fun getCastByMovie(value: String): LiveData<List<CastEntity>>

}