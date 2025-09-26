package com.example.myapplication.feature.dollar.data.mapper

import com.example.myapplication.feature.dollar.data.database.entity.DollarEntity
import com.example.myapplication.feature.dollar.domain.model.DollarModel

fun DollarEntity.toModel(): DollarModel {
    return DollarModel(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        dollarUsdt = dollarUsdt,
        dollarUsdc = dollarUsdc,
        timestamp = timestamp
    )
}

fun DollarModel.toEntity(): DollarEntity {
    return DollarEntity(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        dollarUsdt = dollarUsdt,
        dollarUsdc = dollarUsdc,
        timestamp = System.currentTimeMillis()
    )
}
