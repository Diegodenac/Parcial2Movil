package com.example.myapplication.feature.movie_pages.data.datasorce

import com.example.myapplication.feature.movie_pages.data.database.dao.IMovieDao
import com.example.myapplication.feature.movie_pages.data.mapper.toEntity
import com.example.myapplication.feature.movie_pages.data.mapper.toModel
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel

class MoviesLocalDataSource(
    private val dao: IMovieDao
) {

    suspend fun getMovies(): List<MovieModel> {
        return dao.getMovies().map { it.toModel() }
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun insertMovies(list: List<MovieModel>) {
        val entities = list.map { it.toEntity() }
        dao.insertMovies(entities)
    }

    suspend fun insert(movie: MovieModel) {
        dao.insert(movie.toEntity())
    }
}
