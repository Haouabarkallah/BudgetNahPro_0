package com.budgetnah.pro.presentation.features.goals

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.budgetnah.pro.presentation.core.components.PrimaryButton
import com.budgetnah.pro.presentation.features.goals.components.GoalProgressCard

@Composable
fun GoalsScreen(
    viewModel: GoalViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.padding(16.dp)) {

        Text("🎯 Mes objectifs", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        viewModel.goals.forEach { goal ->
            GoalProgressCard(
                title = goal.title,
                progress = goal.progress
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        PrimaryButton(
            text = "➕ Ajouter objectif",
            onClick = {
                viewModel.addGoal(Goal("Nouveau objectif", 0.1f))
            }
        )
    }
}