package com.example.myapplication.feature.github.domain.repository

import com.example.myapplication.feature.github.domain.model.UserModel

interface IgitHubRepository {
    suspend fun findByNickName(nickName: String): Result<UserModel>
}