package com.budgetnah.pro.presentation.core.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun CurrencySelector(
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit
) {
    val currencies = listOf("XAF", "USD", "EUR")

    Row {
        currencies.forEach {
            TextButton(onClick = { onCurrencySelected(it) }) {
                Text(it)
            }
        }
    }
}