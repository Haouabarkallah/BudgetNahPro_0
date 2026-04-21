package com.budgetnah.pro.presentation.features.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.budgetnah.pro.data.local.entities.Expense
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TrendChart(expenses: List<Expense>) {

    val grouped = expenses.groupBy {
        SimpleDateFormat("dd").format(Date(it.date))
    }

    Column {
        grouped.entries.forEach { (day, list) ->
            Text("$day : ${list.sumOf { it.amount }}")
        }
    }
}