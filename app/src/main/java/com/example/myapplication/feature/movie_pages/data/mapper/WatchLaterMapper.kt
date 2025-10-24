package com.example.myapplication.feature.movie_pages.data.mapper

import com.example.myapplication.feature.movie_pages.data.database.entity.WatchLaterEntity
import com.example.myapplication.feature.movie_pages.domain.model.MovieModel

fun WatchLaterEntity.toModel(): MovieModel {
    return MovieModel(
        title = movieTitle,
        posterPath = posterPath
    )
}

fun MovieModel.toWatchLaterEntity(): WatchLaterEntity {
    return WatchLaterEntity(
        movieTitle = title,
        posterPath = posterPath
    )
}