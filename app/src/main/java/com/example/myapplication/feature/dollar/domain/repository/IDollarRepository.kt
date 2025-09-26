package com.example.myapplication.feature.dollar.domain.repository

import com.example.myapplication.feature.dollar.domain.model.DollarModel
import kotlinx.coroutines.flow.Flow

interface IDollarRepository {
    suspend fun getDollar(): Flow<DollarModel>
    suspend fun getAllFromLocal(): List<DollarModel>
}
