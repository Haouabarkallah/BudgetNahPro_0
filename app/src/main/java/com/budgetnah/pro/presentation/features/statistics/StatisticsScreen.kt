package com.budgetnah.pro.presentation.features.statistics

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.budgetnah.pro.presentation.core.components.BarChartView
import com.budgetnah.pro.presentation.core.components.PieChartView

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel()
) {
    var selectedFilter by remember { mutableStateOf("mois") }
    val userId = 1

    LaunchedEffect(selectedFilter) {
        val now = System.currentTimeMillis()

        val start = when (selectedFilter) {
            "semaine" -> now - (7L * 24 * 60 * 60 * 1000)
            "annee" -> now - (365L * 24 * 60 * 60 * 1000)
            else -> now - (30L * 24 * 60 * 60 * 1000)
        }

        viewModel.loadStats(userId, start, now)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("📊 Statistiques", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))
        // ajout de filtres
        Row {
            TextButton(onClick = { selectedFilter = "semaine" }) { Text("Semaine") }
            TextButton(onClick = { selectedFilter = "mois" }) { Text("Mois") }
            TextButton(onClick = { selectedFilter = "annee" }) { Text("Année") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 💰 Total
        Text(
            text = "Total dépenses : ${viewModel.total} FCFA",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))
        //ajout du chart
        Text("📊 Répartition", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(12.dp))

        PieChartView(data = viewModel.categoryStats)

        Spacer(modifier = Modifier.height(16.dp))

        BarChartView(summaryData = viewModel.categoryStats)

        Spacer(modifier = Modifier.height(12.dp))

        Text("📊 Répartition des dépenses", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // 📂 Par catégorie
        Text("Par catégorie :", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        viewModel.categoryStats.forEach { stat ->
            Text("Catégorie ${stat.categoryId} : ${stat.total} FCFA")


        }
    }
}