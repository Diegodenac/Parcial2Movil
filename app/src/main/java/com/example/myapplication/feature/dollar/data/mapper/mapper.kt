package com.example.myapplication.feature.dollar.data.mapper

import com.example.myapplication.feature.dollar.data.database.entity.DollarEntity
import com.example.myapplication.feature.dollar.domain.model.DollarModel

fun DollarEntity.toModel(): DollarModel{
    return DollarModel(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel
    )
}

fun DollarModel.toEntity(): DollarEntity{
    return DollarEntity(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        timestamp = System.currentTimeMillis()
    )
}