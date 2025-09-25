package com.example.myapplication.feature.loginRecuperatorio.domain.repository

import com.example.myapplication.feature.loginRecuperatorio.domain.model.ChatModel
import com.example.myapplication.feature.loginRecuperatorio.domain.model.UserLoginModel

interface IuserLoginrepository {
    fun authLogin(email: String, password: String): Result<UserLoginModel>
    fun getSupportLink(): Result<ChatModel>
}