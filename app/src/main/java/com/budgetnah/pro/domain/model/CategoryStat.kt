package com.budgetnah.pro.domain.model

data class CategoryStat(
    val categoryId: Int = 0,
    val name: String,
    val total: Double,
    val color: Long = 0xFF808080
)
