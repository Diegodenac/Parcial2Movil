package com.example.myapplication.feature.loginRecuperatorio.domain.usercases

import com.example.myapplication.feature.loginRecuperatorio.domain.model.UserLoginModel
import com.example.myapplication.feature.loginRecuperatorio.domain.repository.IuserLoginrepository

class AuthenticateLogInUseCase( val repository: IuserLoginrepository) {
    fun invoke(email: String, password: String): Result<UserLoginModel>{
        //if(email == null || password == null)
          //  return Result.failure()
        return repository.authLogin(email, password)
    }
}