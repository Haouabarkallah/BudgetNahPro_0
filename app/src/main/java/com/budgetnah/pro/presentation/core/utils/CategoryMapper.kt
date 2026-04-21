package com.budgetnah.pro.presentation.core.utils

fun mapCategoryNameToId(name: String): Int {
    return when (name) {
        "Food 🍔" -> 1
        "Transport 🚗" -> 2
        "Shopping 🛒" -> 3
        else -> 0
    }
}