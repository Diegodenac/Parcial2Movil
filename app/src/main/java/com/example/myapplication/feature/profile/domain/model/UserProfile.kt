package com.example.myapplication.feature.profile.domain.model

import com.example.myapplication.feature.profile.domain.model.vo.Email
import com.example.myapplication.feature.profile.domain.model.vo.ProfilePicturePath
import com.example.myapplication.feature.profile.domain.model.vo.UserName

class UserProfile(
    val userName: UserName,
    val email: Email,
    val pathPicture: ProfilePicturePath
)