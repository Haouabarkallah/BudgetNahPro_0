package com.budgetnah.pro.core.utils

data class ParsedExpense(
    val amount: Double?,
    val date: String?,
    val category: String?
)

fun parseReceipt(text: String): ParsedExpense {

    val amountRegex = Regex("(\\d+[.,]?\\d*)")
    val dateRegex = Regex("(\\d{2}/\\d{2}/\\d{4})")

    val amount = amountRegex.find(text)?.value
        ?.replace(",", ".")
        ?.toDoubleOrNull()

    val date = dateRegex.find(text)?.value

    val category = when {
        text.contains("restaurant", true) -> "Food 🍔"
        text.contains("uber", true) -> "Transport 🚗"
        text.contains("market", true) -> "Shopping 🛒"
        else -> "Autre"
    }

    return ParsedExpense(amount, date, category)
}
fun cleanText(text: String): String {
    return text.replace("\n", " ").trim()
}
