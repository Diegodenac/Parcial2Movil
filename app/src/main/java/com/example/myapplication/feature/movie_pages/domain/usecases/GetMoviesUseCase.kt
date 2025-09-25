package com.example.myapplication.feature.movie_pages.domain.usecases

import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import com.example.myapplication.feature.movie_pages.domain.repository.IMoviesRepository

class GetMoviesUseCase(
    val repository: IMoviesRepository
) {
    suspend fun invoke(): Result<List<MovieModel>>{
        return repository.getMovies()
    }
}