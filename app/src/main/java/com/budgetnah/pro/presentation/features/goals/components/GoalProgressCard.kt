package com.budgetnah.pro.presentation.features.goals.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoalProgressCard(title: String, progress: Float) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title)
            LinearProgressIndicator(progress = { progress })
        }
    }
}