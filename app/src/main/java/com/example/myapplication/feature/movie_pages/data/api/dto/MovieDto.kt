package com.example.myapplication.feature.movie_pages.data.api.dto

import com.google.gson.annotations.SerializedName

data class MovieDto (@SerializedName("poster_path") val posterPath:String, val title:String)

//https://image.tmdb.org/t/p/w185/ + posterPath