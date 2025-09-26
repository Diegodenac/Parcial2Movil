package com.example.myapplication.feature.movie_pages.data.mapper

import com.example.myapplication.feature.movie_pages.data.database.entity.MovieEntity
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel

fun MovieEntity.toModel(): MovieModel {
    return MovieModel(
        title = title ?: "",
        posterPath = posterPath ?: ""
    )
}

fun MovieModel.toEntity(): MovieEntity {
    return MovieEntity(
        title = title,
        posterPath = posterPath
    )
}
