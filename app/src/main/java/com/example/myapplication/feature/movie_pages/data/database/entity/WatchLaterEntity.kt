package com.example.myapplication.feature.movie_pages.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watch_later")
data class WatchLaterEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_title")
    val movieTitle: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "added_timestamp")
    val addedTimestamp: Long = System.currentTimeMillis()
)