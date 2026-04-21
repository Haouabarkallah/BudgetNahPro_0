package com.budgetnah.pro.presentation.features.statistics.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StatsHeader(title: String) {
    Text(title, style = MaterialTheme.typography.headlineMedium)
}