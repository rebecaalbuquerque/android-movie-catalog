package com.albuquerque.moviecatalog.app.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieEntity(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "originalTitle") val originalTitle: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "overview") val overview: String,
        @ColumnInfo(name = "releaseDate") val releaseDate: String,
        @ColumnInfo(name = "poster") val poster: String,
        @ColumnInfo(name = "category") val category: String
)