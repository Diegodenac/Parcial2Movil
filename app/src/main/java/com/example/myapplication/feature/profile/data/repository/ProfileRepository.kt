package com.example.myapplication.feature.profile.data.repository

import com.example.myapplication.feature.github.domain.model.UserModel
import com.example.myapplication.feature.profile.domain.model.UserProfile
import com.example.myapplication.feature.profile.domain.repository.IuserProfileRepository

class ProfileRepository: IuserProfileRepository {
    override fun getProfile(): Result<UserProfile> {
        return Result.success(UserProfile("rick_069", "ricky_rikoso@gmail.com","https://www.vice.com/wp-content/uploads/sites/2/2017/09/1505975578830-1505732141447-rnm.jpeg"))
    }
}