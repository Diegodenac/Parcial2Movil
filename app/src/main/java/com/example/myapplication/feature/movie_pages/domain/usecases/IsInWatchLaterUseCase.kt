package com.example.myapplication.feature.movie_pages.domain.usecases

import com.example.myapplication.feature.movie_pages.domain.repository.IWatchLaterRepository

class IsInWatchLaterUseCase(
    private val repository: IWatchLaterRepository
) {
    suspend fun invoke(movieTitle: String): Boolean {
        return repository.isInWatchLater(movieTitle)
    }
}