package com.example.myapplication.feature.movie_pages.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.feature.movie_pages.data.database.dao.IMovieDao
import com.example.myapplication.feature.movie_pages.data.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): IMovieDao

    companion object {
        @Volatile
        private var Instance: MoviesRoomDatabase? = null

        fun getDatabase(context: Context): MoviesRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MoviesRoomDatabase::class.java,
                    "movies_db"
                ).build().also { Instance = it }
            }
        }
    }
}