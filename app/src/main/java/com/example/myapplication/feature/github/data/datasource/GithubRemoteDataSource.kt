package com.example.myapplication.feature.github.data.datasource

import com.example.myapplication.feature.github.data.api.GithubService
import com.example.myapplication.feature.github.data.api.dto.GitaliasDto

class GithubRemoteDataSource(
    val githubService: GithubService
) {
    suspend fun getUser(nick:String): Result<GitaliasDto>{
        val response=githubService.getInfoAvatar(nick)
        if (response.isSuccessful){
            return Result.success(response.body()!!)
        } else{
            return Result.failure(Exception("Error al obtener el usuario"))
        }
    }
}