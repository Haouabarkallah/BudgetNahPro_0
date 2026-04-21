package com.budgetnah.pro.presentation.features.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.budgetnah.pro.domain.model.CategoryStat

@Composable
fun TopCategoryItem(stat: CategoryStat) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stat.name)
        Text("${stat.total}")
    }
}