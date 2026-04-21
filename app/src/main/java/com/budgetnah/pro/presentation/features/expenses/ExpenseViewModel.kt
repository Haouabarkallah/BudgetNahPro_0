package com.budgetnah.pro.presentation.features.expenses

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetnah.pro.domain.repository.ExpenseRepository
import com.budgetnah.pro.data.local.entities.Expense
import com.budgetnah.pro.data.local.entities.Category
import com.budgetnah.pro.data.local.dao.CategoryExpenseSummary
import com.budgetnah.pro.data.export.PdfExporter
import com.budgetnah.pro.data.export.ExcelExporter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {

    val expenses = repository.getExpenses(1)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }
    fun exportPdf(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {

            val exporter = PdfExporter(context)
            val file = exporter.export(expenses.value)

            // tu peux notifier UI après
        }
    }

    fun exportExcel(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {

            val exporter = ExcelExporter(context)
            val file = exporter.export(expenses.value)
        }
    }
}
data class ExpenseUiState(
    val expenses: List<Expense> = emptyList(),
    val isLoading: Boolean = false
)