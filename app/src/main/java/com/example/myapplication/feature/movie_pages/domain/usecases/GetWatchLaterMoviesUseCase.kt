package com.example.myapplication.feature.movie_pages.domain.usecases

import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import com.example.myapplication.feature.movie_pages.domain.repository.IWatchLaterRepository
import kotlinx.coroutines.flow.Flow

class GetWatchLaterMoviesUseCase(
    private val repository: IWatchLaterRepository
) {
    fun invoke(): Flow<List<MovieModel>> {
        return repository.getWatchLaterMovies()
    }
}