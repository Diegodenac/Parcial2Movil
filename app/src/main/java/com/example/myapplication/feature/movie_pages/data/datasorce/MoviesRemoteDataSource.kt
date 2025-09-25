package com.example.myapplication.feature.movie_pages.data.datasorce

import com.example.myapplication.feature.movie_pages.data.api.MoviesService
import com.example.myapplication.feature.movie_pages.data.api.dto.MoviesPageDto

class MoviesRemoteDataSource(
    val moviesService: MoviesService
) {
    suspend fun getMovies(): Result<MoviesPageDto>{
        val response=moviesService.getPageMovies()
        if(response.isSuccessful){
            return Result.success(response.body()!!)
        } else{
            val errorBody = response.errorBody()?.string()
            return Result.failure(
                Exception("Error ${response.code()} - ${response.message()} - $errorBody")
            )
        }
    }
}