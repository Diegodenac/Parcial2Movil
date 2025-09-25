package com.example.myapplication.feature.profile.domain.repository

import com.example.myapplication.feature.profile.domain.model.UserProfile

interface IuserProfileRepository {
    fun getProfile(): Result<UserProfile>
}