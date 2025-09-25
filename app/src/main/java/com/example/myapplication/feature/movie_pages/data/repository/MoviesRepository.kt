package com.example.myapplication.feature.movie_pages.data.repository

import com.example.myapplication.feature.movie_pages.data.datasorce.MoviesRemoteDataSource
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import com.example.myapplication.feature.movie_pages.domain.repository.IMoviesRepository

class MoviesRepository(
    val remoteDataSource: MoviesRemoteDataSource
): IMoviesRepository {
    override suspend fun getMovies(): Result<List<MovieModel>>{
        val response = remoteDataSource.getMovies()
        response.fold(
            onSuccess = {
                it ->
                return Result.success(it.results.map {
                    movieDto ->
                    MovieModel(
                        title = movieDto.title,
                        posterPath = movieDto.posterPath
                    )
                })
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }
}