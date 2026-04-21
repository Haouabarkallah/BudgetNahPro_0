package com.budgetnah.pro.presentation.core.components

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.budgetnah.pro.domain.model.CategoryStat
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

@Composable
fun PieChartView(data: List<CategoryStat>) {

    AndroidView(
        factory = { context ->
            PieChart(context).apply {

                val entries = data.map {
                    PieEntry(it.total.toFloat(), it.name)
                }

                val dataSet = PieDataSet(entries, "Répartition")

                dataSet.colors = data.map { it.color.toInt() }

                dataSet.valueTextSize = 12f

                /*dataSet.colors = listOf(
                    Color.BLUE,
                    Color.RED,
                    Color.GREEN,
                    Color.YELLOW
                )*/

                val pieData = PieData(dataSet)
                this.data = pieData

                description.isEnabled = false
                centerText = "Dépenses"
                setEntryLabelTextSize(12f)

                animateY(1000)

                invalidate()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}