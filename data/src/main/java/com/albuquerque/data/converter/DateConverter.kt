package com.albuquerque.data.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(date: Long): Date = Date(date)

    @TypeConverter
    fun fromDate(date: Date): Long = date.time

}