package com.example.myapplication.feature.dollar.domain.model

data class DollarModel(
    val dollarOfficial: String? = null,
    val dollarParallel: String? = null,
    val dollarUsdt: String? = null,
    val dollarUsdc: String? = null,
    val timestamp: Long = 0
)
