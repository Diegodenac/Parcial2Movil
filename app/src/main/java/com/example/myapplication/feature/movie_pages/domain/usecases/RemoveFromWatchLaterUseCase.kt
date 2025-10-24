package com.example.myapplication.feature.movie_pages.domain.usecases

import com.example.myapplication.feature.movie_pages.domain.repository.IWatchLaterRepository

class RemoveFromWatchLaterUseCase(
    private val repository: IWatchLaterRepository
) {
    suspend fun invoke(movieTitle: String): Result<Unit> {
        return repository.removeFromWatchLater(movieTitle)
    }
}