package com.example.myapplication.feature.profile.domain.usercases

import com.example.myapplication.feature.profile.domain.model.UserProfile
import com.example.myapplication.feature.profile.domain.repository.IuserProfileRepository

class GetProfileUseCase( val repository: IuserProfileRepository) {
    fun invoke(): Result<UserProfile>{
        return repository.getProfile()
    }
}