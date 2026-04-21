package com.budgetnah.pro.presentation.features.statistics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetnah.pro.data.local.dao.CategoryDao
import com.budgetnah.pro.data.local.dao.ExpenseDao
import com.budgetnah.pro.domain.model.CategoryStat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val categoryDao: CategoryDao
) : ViewModel() {

    var total by mutableStateOf(0.0)
        private set

    var categoryStats by mutableStateOf<List<CategoryStat>>(emptyList())
        private set
    //var selectedFilter by remember { mutableStateOf("mois") }

    fun loadStats(userId: Int, start: Long, end: Long) {
        viewModelScope.launch {
            total = expenseDao.getTotalExpensesBetween(userId, start, end) ?: 0.0

            val rawStats = expenseDao.getExpensesByCategory(userId, start, end)
            val categories = categoryDao.getAllCategories(userId).first()

            categoryStats = rawStats.map { stat ->
                val category = categories.find { it.id == stat.categoryId }

                CategoryStat(
                    categoryId = stat.categoryId,
                    name = category?.name ?: "Unknown",
                    total = stat.total,
                    color = category?.color ?: 0xFF808080
                )
            }
        }
    }
}