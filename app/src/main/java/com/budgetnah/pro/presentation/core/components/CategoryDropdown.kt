package com.budgetnah.pro.presentation.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.budgetnah.pro.presentation.features.categories.CategoriesViewModel

@Composable
fun CategoryDropdown(
    onCategorySelected: (Int) -> Unit,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val categories = viewModel.categories

    var expanded by remember { mutableStateOf(false) }
    var selectedCategoryName by remember { mutableStateOf("Choisir une catégorie") }

    Column {

        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📂 $selectedCategoryName")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->

                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        selectedCategoryName = category.name
                        expanded = false
                        onCategorySelected(category.id)
                    }
                )
            }
        }
    }
}

