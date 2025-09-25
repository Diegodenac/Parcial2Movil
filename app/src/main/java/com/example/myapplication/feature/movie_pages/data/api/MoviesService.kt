package com.example.myapplication.feature.movie_pages.data.api

import com.example.myapplication.feature.movie_pages.data.api.dto.MovieDto
import com.example.myapplication.feature.movie_pages.data.api.dto.MoviesPageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.myapplication.R

interface MoviesService {
    @GET("discover/movie")
    suspend fun getPageMovies(@Query("sort_by") sortBy: String = "popularity.desc",
                              @Query("api_key") apiKey: String = "fa3e844ce31744388e07fa47c7c5d8c3"):Response<MoviesPageDto>//R.string.api_key.toString()):Response<MoviesPageDto> REVISAR PARA RECIBIR EL API KEY DESDE LA CARPETA DE RECURSOS
}