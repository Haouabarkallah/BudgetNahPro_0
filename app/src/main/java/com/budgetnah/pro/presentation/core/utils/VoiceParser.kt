package com.budgetnah.pro.core.utils

data class VoiceCommand(
    val amount: Double?,
    val category: String?
)

fun parseVoice(text: String): VoiceCommand {

    val amount = Regex("\\d+")
        .find(text)
        ?.value
        ?.toDoubleOrNull()

    val category = when {
        text.contains("transport", true) -> "Transport 🚗"
        text.contains("food", true) -> "Food 🍔"
        text.contains("shopping", true) -> "Shopping 🛒"
        else -> "Autre"
    }

    return VoiceCommand(amount, category)
}