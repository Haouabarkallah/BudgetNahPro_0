// presentation/dashboard/DashboardScreen.kt
package com.budgetnah.pro.presentation.features.dashboard

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.budgetnah.pro.data.local.entities.Expense
import com.budgetnah.pro.presentation.features.dashboard.components.BalanceCard
import com.budgetnah.pro.presentation.features.dashboard.components.DashboardHeader
import com.budgetnah.pro.presentation.features.dashboard.components.TrendChart

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val expenses by viewModel.expenses.collectAsState(initial = emptyList<Expense>())

    if (uiState.isLoading) {
        ShimmerDashboard()
    } else {
        AnimatedDashboardContent(uiState, expenses, navController, viewModel)
    }
}

@Composable
fun ShimmerDashboard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.size(150.dp, 30.dp).shimmerEffect())
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth().height(150.dp).shimmerEffect())
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(1f).height(40.dp).shimmerEffect())
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f).height(40.dp).shimmerEffect())
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f).height(40.dp).shimmerEffect())
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )

    background(brush = brush, shape = RoundedCornerShape(8.dp))
}

@Composable
fun AnimatedDashboardContent(
    uiState: DashboardUiState,
    expenses: List<Expense>,
    navController: NavController,
    viewModel: DashboardViewModel
) {
    val alpha by animateFloatAsState(targetValue = 1f, animationSpec = tween(1000), label = "alpha")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { DashboardHeader() }

        item {
            // Note: Updated BalanceCard call to match existing signature if necessary, 
            // or we might need to update BalanceCard to accept income/expenses
            BalanceCard(total = uiState.totalExpenses) 
        }

        item {
            TrendChart(expenses = expenses)
        }

        item {
            Text("Catégories", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        items(uiState.categorySpending) { spending ->
            CategoryItem(spending)
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { navController.navigate("add_expense") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f).height(50.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Ajouter")
                }
                Spacer(Modifier.width(16.dp))
                OutlinedButton(
                    onClick = { navController.navigate("statistics") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f).height(50.dp)
                ) {
                    Icon(Icons.Default.BarChart, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Stats")
                }
            }
        }
    }
}

@Composable
fun CategoryItem(spending: CategorySpending) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(spending.color)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(spending.categoryName, fontWeight = FontWeight.SemiBold)
                Text("Dépenses", style = MaterialTheme.typography.bodySmall)
            }
            Text(
                "${spending.amount} FCFA",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
