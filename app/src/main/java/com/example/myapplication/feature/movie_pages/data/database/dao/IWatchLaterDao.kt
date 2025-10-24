package com.example.myapplication.feature.movie_pages.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.feature.movie_pages.data.database.entity.WatchLaterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IWatchLaterDao {

    // Obtener todas las películas de "Ver después"
    @Query("SELECT * FROM watch_later ORDER BY added_timestamp DESC")
    fun getWatchLaterMovies(): Flow<List<WatchLaterEntity>>

    // Verificar si una película está en "Ver después"
    @Query("SELECT EXISTS(SELECT 1 FROM watch_later WHERE movie_title = :movieTitle)")
    suspend fun isInWatchLater(movieTitle: String): Boolean

    // Agregar película a "Ver después"
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchLater(movie: WatchLaterEntity)

    // Eliminar película de "Ver después"
    @Query("DELETE FROM watch_later WHERE movie_title = :movieTitle")
    suspend fun removeFromWatchLater(movieTitle: String)

    // Obtener todos los títulos en "Ver después" (para filtrar fácilmente)
    @Query("SELECT movie_title FROM watch_later")
    suspend fun getWatchLaterTitles(): List<String>
}