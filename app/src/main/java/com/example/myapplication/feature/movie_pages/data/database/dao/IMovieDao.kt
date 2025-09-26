package com.example.myapplication.feature.movie_pages.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.feature.movie_pages.data.database.entity.MovieEntity

@Dao
interface IMovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}
