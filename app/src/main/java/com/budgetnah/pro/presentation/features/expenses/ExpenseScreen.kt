package com.budgetnah.pro.presentation.features.expenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.budgetnah.pro.presentation.features.expenses.components.ExpenseFilterBar
import com.budgetnah.pro.presentation.features.expenses.components.ExpenseHeader
import com.budgetnah.pro.presentation.core.components.ExpenseCard

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onAddClick: () -> Unit
) {
    val expenses by viewModel.expenses.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            ExpenseHeader()

            Spacer(modifier = Modifier.height(8.dp))

            ExpenseFilterBar()

            Spacer(modifier = Modifier.height(8.dp))

            // 🔥 Empty state
            if (expenses.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Aucune dépense pour le moment")
                }
            } else {

                LazyColumn(
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(expenses) { expense ->

                        ExpenseCard(
                            amount = "${expense.amount} FCFA",
                            category = "Cat ${expense.categoryId}",
                            date = expense.date.toString(),
                            onDelete = {
                                viewModel.deleteExpense(expense)
                            }
                        )
                    }
                }
            }
        }
    }
}