package com.example.myapplication.feature.movie_pages.domain.repository

import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IWatchLaterRepository {
    fun getWatchLaterMovies(): Flow<List<MovieModel>>
    suspend fun addToWatchLater(movie: MovieModel): Result<Unit>
    suspend fun removeFromWatchLater(movieTitle: String): Result<Unit>
    suspend fun isInWatchLater(movieTitle: String): Boolean
}