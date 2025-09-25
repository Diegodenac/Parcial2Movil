package com.example.myapplication.feature.github.domain.usercases

import com.example.myapplication.feature.github.domain.model.UserModel
import com.example.myapplication.feature.github.domain.repository.IgitHubRepository

class FindByNicknameUseCase(
    val repository: IgitHubRepository
) {
    suspend fun invoke(nickName: String): Result<UserModel>{
        //devuelve la imagen del usuario
        return repository.findByNickName(nickName)
    }
}