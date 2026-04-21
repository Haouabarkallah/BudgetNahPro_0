// presentation/dashboard/PieChart.kt
package com.budgetnah.pro.presentation.features.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.budgetnah.pro.presentation.features.dashboard.CategorySpending

@Composable
fun PieChart(
    data: List<CategorySpending>,
    modifier: Modifier = Modifier
) {
    val total = data.sumOf { it.amount }.toFloat()
    if (total == 0f) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text("No data to display")
        }
        return
    }

    Canvas(modifier = modifier) {
        var startAngle = 0f
        data.forEach { entry ->
            val sweepAngle = (entry.amount.toFloat() / total) * 360f
            drawPieSlice(
                center = center,
                radius = size.minDimension / 2,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                color = entry.color
            )
            startAngle += sweepAngle
        }

        // Draw center hole for donut effect (optional)
        drawCircle(
            color = Color.White,
            radius = size.minDimension / 4,
            center = center
        )
    }
}

private fun DrawScope.drawPieSlice(
    center: Offset,
    radius: Float,
    startAngle: Float,
    sweepAngle: Float,
    color: Color
) {
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = true,
        topLeft = Offset(center.x - radius, center.y - radius),
        size = Size(radius * 2, radius * 2)
    )
}