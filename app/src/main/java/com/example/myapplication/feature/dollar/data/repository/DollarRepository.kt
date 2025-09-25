package com.example.myapplication.feature.dollar.data.repository

import com.example.myapplication.feature.dollar.data.datasource.DollarLocalDataSource
import com.example.myapplication.feature.dollar.data.datasource.RealTimeRemoteDataSource
import com.example.myapplication.feature.dollar.domain.model.DollarModel
import com.example.myapplication.feature.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class DollarRepository(
    val realTimeRemoteDataSource: RealTimeRemoteDataSource,
    val localDataSource: DollarLocalDataSource
): IDollarRepository {
    override suspend fun getDollar(): Flow<DollarModel>{
//        return flow {
//            emit(DollarModel("6.96", "12.6"))
//        }
    return realTimeRemoteDataSource.getDollarUpdates()
        .onEach {
            localDataSource.insert(it)
        }
    }
}