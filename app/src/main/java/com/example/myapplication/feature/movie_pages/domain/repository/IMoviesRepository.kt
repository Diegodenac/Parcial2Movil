package com.example.myapplication.feature.movie_pages.domain.repository

import com.example.myapplication.feature.movie_pages.domain.model.MovieModel

interface IMoviesRepository {
    suspend fun getMovies(): Result<List<MovieModel>>
}