package com.example.myapplication.feature.dollar.domain.usecase

import com.example.myapplication.feature.dollar.domain.model.DollarModel
import com.example.myapplication.feature.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow

class FetchDollarUseCase(
    private val repository: IDollarRepository
) {
    suspend fun invoke() = repository.getDollar()

    suspend fun getAllFromLocal(): List<DollarModel> {
        return repository.getAllFromLocal()
    }
}
