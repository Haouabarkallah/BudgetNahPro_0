package com.budgetnah.pro.core.utils

fun extractAmount(text: String): Double? {
    val regex = Regex("\\d+\\.\\d+|\\d+")
    return regex.find(text)?.value?.toDoubleOrNull()
}