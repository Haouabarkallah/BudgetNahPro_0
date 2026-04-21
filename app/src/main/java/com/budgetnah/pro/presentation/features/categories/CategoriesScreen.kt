package com.budgetnah.pro.presentation.features.categories

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.budgetnah.pro.presentation.core.components.PrimaryButton

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {

    var newCategory by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("📂 Catégories", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newCategory,
            onValueChange = { newCategory = it },
            label = { Text("Nouvelle catégorie") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        PrimaryButton(
            text = "Ajouter",
            onClick = {
                viewModel.addCategory(newCategory)
                newCategory = ""
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        viewModel.categories.forEach {
            Text(it.name)
        }
    }
}