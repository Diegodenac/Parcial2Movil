package com.example.myapplication.feature.movie_pages.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.feature.movie_pages.data.database.dao.IMovieDao
import com.example.myapplication.feature.movie_pages.data.database.dao.IWatchLaterDao
import com.example.myapplication.feature.movie_pages.data.database.entity.MovieEntity
import com.example.myapplication.feature.movie_pages.data.database.entity.WatchLaterEntity

@Database(
    entities = [MovieEntity::class, WatchLaterEntity::class],
    version = 2,
    exportSchema = false
)
abstract class MoviesRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): IMovieDao
    abstract fun watchLaterDao(): IWatchLaterDao

    companion object {
        @Volatile
        private var Instance: MoviesRoomDatabase? = null

        // Migración de versión 1 a 2
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Crear la nueva tabla watch_later
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS watch_later (
                        movie_title TEXT PRIMARY KEY NOT NULL,
                        poster_path TEXT NOT NULL,
                        added_timestamp INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        fun getDatabase(context: Context): MoviesRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MoviesRoomDatabase::class.java,
                    "movies_db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}