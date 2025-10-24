package com.example.myapplication.feature.movie_pages.data.repository

import com.example.myapplication.feature.movie_pages.data.database.dao.IWatchLaterDao
import com.example.myapplication.feature.movie_pages.data.mapper.toModel
import com.example.myapplication.feature.movie_pages.data.mapper.toWatchLaterEntity
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import com.example.myapplication.feature.movie_pages.domain.repository.IWatchLaterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WatchLaterRepository(
    private val watchLaterDao: IWatchLaterDao
) : IWatchLaterRepository {

    override fun getWatchLaterMovies(): Flow<List<MovieModel>> {
        return watchLaterDao.getWatchLaterMovies().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addToWatchLater(movie: MovieModel): Result<Unit> {
        return try {
            watchLaterDao.addToWatchLater(movie.toWatchLaterEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeFromWatchLater(movieTitle: String): Result<Unit> {
        return try {
            watchLaterDao.removeFromWatchLater(movieTitle)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isInWatchLater(movieTitle: String): Boolean {
        return try {
            watchLaterDao.isInWatchLater(movieTitle)
        } catch (e: Exception) {
            false
        }
    }
}