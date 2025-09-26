package com.example.myapplication.feature.movie_pages.data.repository

import com.example.myapplication.feature.movie_pages.data.datasorce.MoviesLocalDataSource
import com.example.myapplication.feature.movie_pages.data.datasorce.MoviesRemoteDataSource
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel
import com.example.myapplication.feature.movie_pages.domain.repository.IMoviesRepository

class MoviesRepository(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource
) : IMoviesRepository {

    override suspend fun getMovies(): Result<List<MovieModel>> {
        val response = remoteDataSource.getMovies()
        response.fold(
            onSuccess = { pageDto ->
                val movies = pageDto.results.map { dto ->
                    MovieModel(
                        title = dto.title,
                        posterPath = dto.posterPath
                    )
                }
                // Guardar en base local
                localDataSource.deleteAll()
                localDataSource.insertMovies(movies)

                return Result.success(movies)
            },
            onFailure = {
                // Si falla la API, recuperamos de BD local
                val localMovies = localDataSource.getMovies()
                if (localMovies.isNotEmpty()) {
                    return Result.success(localMovies)
                }
                return Result.failure(it)
            }
        )
    }
}
