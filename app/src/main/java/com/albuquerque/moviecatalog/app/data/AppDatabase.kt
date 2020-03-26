package com.albuquerque.moviecatalog.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.albuquerque.moviecatalog.app.data.dao.MovieDao
import com.albuquerque.moviecatalog.app.data.entity.MovieEntity

@Database(
        version = 1,
        exportSchema = false,
        entities = [MovieEntity::class]
)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "moviecatalog"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: synchronized(this) {buildDatabase(context)}
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                    context, AppDatabase::class.java,
                    DATABASE_NAME
            )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
        }

    }

    abstract val movieDAO: MovieDao

}