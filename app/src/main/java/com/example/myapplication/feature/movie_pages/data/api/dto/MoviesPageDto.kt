package com.example.myapplication.feature.movie_pages.data.api.dto

import com.google.gson.annotations.SerializedName

data class MoviesPageDto(//@SerializedName("results")
                         val results: List<MovieDto>, val page: Int)