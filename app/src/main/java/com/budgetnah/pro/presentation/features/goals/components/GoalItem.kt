package com.budgetnah.pro.presentation.features.goals.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GoalItem(name: String, progress: Float) {
    Column {
        Text(name)
        LinearProgressIndicator(progress = { progress })
    }
}