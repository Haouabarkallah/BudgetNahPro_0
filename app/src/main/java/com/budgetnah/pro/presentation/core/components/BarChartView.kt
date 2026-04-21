package com.budgetnah.pro.presentation.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.budgetnah.pro.domain.model.CategoryStat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

@Composable
fun BarChartView(summaryData: List<CategoryStat>) {

    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                setFitBars(true)
            }
        },
        update = { barChart ->
            val entries = summaryData.mapIndexed { index, item ->
                BarEntry(index.toFloat(), item.total.toFloat())
            }

            val dataSet = BarDataSet(entries, "Dépenses").apply {
                colors = summaryData.map { it.color.toInt() }
                valueTextSize = 12f
            }

            barChart.data = BarData(dataSet)
            barChart.animateY(1000)
            barChart.invalidate()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}