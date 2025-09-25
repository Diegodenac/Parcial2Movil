package com.example.myapplication.feature.loginRecuperatorio.domain.usercases

import com.example.myapplication.feature.loginRecuperatorio.domain.model.ChatModel
import com.example.myapplication.feature.loginRecuperatorio.domain.repository.IuserLoginrepository

class GoToSupportUseCase(val repository: IuserLoginrepository) {
    fun invoke(): Result<ChatModel>{
        return repository.getSupportLink()
    }
}