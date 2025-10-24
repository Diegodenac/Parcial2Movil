package com.example.myapplication.feature.movie_pages.domain.usecases

import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import com.example.myapplication.feature.movie_pages.domain.repository.IWatchLaterRepository

class AddToWatchLaterUseCase(
    private val repository: IWatchLaterRepository
) {
    suspend fun invoke(movie: MovieModel): Result<Unit> {
        return repository.addToWatchLater(movie)
    }
}