package com.example.myapplication.feature.github.data.api

import com.example.myapplication.feature.github.data.api.dto.GitaliasDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{githubLogin}")
    suspend fun getInfoAvatar(@Path("githubLogin") githubLogin: String):
            Response<GitaliasDto>
}