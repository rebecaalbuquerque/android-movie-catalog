package com.albuquerque.moviecatalog.app.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MovieEntity(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "originalTitle") val originalTitle: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "overview") val overview: String,
        @ColumnInfo(name = "releaseDate") val releaseDate: String,
        @ColumnInfo(name = "poster") val poster: String,
        @ColumnInfo(name = "backdrop") val backdrop: String,
        @ColumnInfo(name = "category") val category: String,
        @ColumnInfo(name = "fetchAt") val fetchAt: Date
)

fun MovieEntity.isEqual(other: MovieEntity): Boolean {

    return this.id == other.id &&
            this.originalTitle == other.originalTitle &&
            this.title == other.title &&
            this.overview == other.overview &&
            this.releaseDate == other.releaseDate &&
            this.poster == other.poster &&
            this.backdrop == other.backdrop &&
            this.category == other.category

}