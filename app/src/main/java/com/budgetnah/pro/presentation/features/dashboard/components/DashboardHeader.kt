package com.budgetnah.pro.presentation.features.dashboard.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DashboardHeader() {
    Text("Dashboard 📊", style = MaterialTheme.typography.headlineLarge)
}