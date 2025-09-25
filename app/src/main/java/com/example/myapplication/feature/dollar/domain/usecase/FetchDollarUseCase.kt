package com.example.myapplication.feature.dollar.domain.usecase

import com.example.myapplication.feature.dollar.domain.model.DollarModel
import com.example.myapplication.feature.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow

class FetchDollarUseCase(
    val repository: IDollarRepository
) {
    suspend fun invoke(): Flow<DollarModel> {
        return repository.getDollar()
    }
}