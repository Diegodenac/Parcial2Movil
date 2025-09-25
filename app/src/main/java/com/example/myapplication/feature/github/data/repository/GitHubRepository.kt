package com.example.myapplication.feature.github.data.repository

import com.example.myapplication.feature.github.data.datasource.GithubRemoteDataSource
import com.example.myapplication.feature.github.domain.model.UserModel
import com.example.myapplication.feature.github.domain.repository.IgitHubRepository

class GitHubRepository(
    val remoteDataSource: GithubRemoteDataSource
) : IgitHubRepository {
    override suspend fun findByNickName(nickName: String): Result<UserModel> {
        if(nickName.isEmpty()){
            return Result.failure(Exception("El campo no puede estar vacio"))
        }
        val response = remoteDataSource.getUser(nickName)
        response.fold(
            onSuccess = {
                it->
                return Result.success(UserModel(
                    nickName = it.login,
                    pathURL = it.url
                ))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
        //return Result.success(UserModel("rick", "https://www.vice.com/wp-content/uploads/sites/2/2017/09/1505975578830-1505732141447-rnm.jpeg"))
    }
}