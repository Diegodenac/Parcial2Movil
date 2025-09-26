package com.example.myapplication.feature.movie_pages.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis()
)
