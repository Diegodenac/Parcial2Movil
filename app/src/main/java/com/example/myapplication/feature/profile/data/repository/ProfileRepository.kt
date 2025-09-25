package com.example.myapplication.feature.profile.data.repository

import com.example.myapplication.feature.github.domain.model.UserModel
import com.example.myapplication.feature.profile.domain.model.UserProfile
import com.example.myapplication.feature.profile.domain.model.vo.Email
import com.example.myapplication.feature.profile.domain.model.vo.ProfilePicturePath
import com.example.myapplication.feature.profile.domain.model.vo.UserName
import com.example.myapplication.feature.profile.domain.repository.IuserProfileRepository

class ProfileRepository: IuserProfileRepository {
    override fun getProfile(): Result<UserProfile> {
        return Result.success(UserProfile(
            UserName.create("rick_069"),
            Email.create("ricky_rikoso@gmail.com"),
            ProfilePicturePath.create("https://www.vice.com/wp-content/uploads/sites/2/2017/09/1505975578830-1505732141447-rnm.jpeg")
        ))
    }
}