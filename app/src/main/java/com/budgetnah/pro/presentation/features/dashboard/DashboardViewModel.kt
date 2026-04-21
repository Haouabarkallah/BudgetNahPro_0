// presentation/dashboard/DashboardViewModel.kt
package com.budgetnah.pro.presentation.features.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetnah.pro.domain.model.CategoryStat
import com.budgetnah.pro.domain.repository.ExpenseRepository
import com.budgetnah.pro.domain.usecase.dashboard.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class DashboardUiState(
    val totalBalance: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalExpenses: Double = 0.0,
    val categorySpending: List<CategorySpending> = emptyList(),
    val budgetAlerts: List<BudgetAlert> = emptyList(),
    val isLoading: Boolean = true
)

data class CategorySpending(
    val categoryId: Int,
    val categoryName: String,
    val amount: Double,
    val color: Color
)

data class BudgetAlert(
    val categoryId: Int,
    val categoryName: String,
    val budget: Double,
    val spent: Double,
    val percentage: Double,
    val exceeded: Boolean
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getUserUseCase: GetLoggedInUserUseCase,
    private val getExpensesUseCase: GetExpensesForUserUseCase,
    private val getBudgetLimitsUseCase: GetBudgetLimitsForUserUseCase,
    private val getCategoriesUseCase: GetCategoriesForUserUseCase,
    private val repository: ExpenseRepository
) : ViewModel() {

    val expenses = repository.getExpenses(1)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    var selectedFilter by mutableStateOf("Mois")
        private set
    fun changeFilter(filter: String) {
        selectedFilter = filter
    }

    fun getTotal(): Double {
        return expenses.value.sumOf { it.amount }
    }

    fun getCategoryStats(): List<CategoryStat> {
        return expenses.value
            .groupBy { it.categoryId }
            .map {
                CategoryStat(
                    categoryId = it.key,
                    name = "Cat ${it.key}",
                    total = it.value.sumOf { e -> e.amount }
                )
            }
    }

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private var currentUserId: Int = -1

    init {
        loadUserAndData()
    }

    private fun loadUserAndData() {
        viewModelScope.launch {
            getUserUseCase().collect { user ->
                if (user != null) {
                    currentUserId = user.id
                    repository.fetchFromCloud(currentUserId)
                    loadDashboardData()
                } else {
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    private suspend fun loadDashboardData() {
        _uiState.update { it.copy(isLoading = true) }
        val startOfMonth = getStartOfMonth()
        val endOfMonth = getEndOfMonth()

        val expenses = getExpensesUseCase(currentUserId, startOfMonth, endOfMonth)
        val budgets = getBudgetLimitsUseCase(currentUserId)
        val categories = getCategoriesUseCase(currentUserId).first()

        val totalExpenses = expenses.sumOf { it.amount }
        // For simplicity, totalIncome is hardcoded or from a separate source. We'll assume a fixed monthly income for demo.
        val totalIncome = 5000.0
        val totalBalance = totalIncome - totalExpenses

        // Spending by category
        val categorySpending = categories.map { category ->
            val amount = expenses.filter { it.categoryId == category.id }.sumOf { it.amount }
            CategorySpending(
                categoryId = category.id,
                categoryName = category.name,
                amount = amount,
                color = Color(category.color)
            )
        }.filter { it.amount > 0 }

        // Budget alerts
        val alerts = mutableListOf<BudgetAlert>()
        budgets.forEach { budget ->
            val spent = expenses.filter { it.categoryId == budget.categoryId }.sumOf { it.amount }
            val percentage = if (budget.amount > 0) (spent / budget.amount) * 100 else 0.0
            val exceeded = spent > budget.amount
            val categoryName = categories.find { it.id == budget.categoryId }?.name ?: "Unknown"
            alerts.add(
                BudgetAlert(
                    categoryId = budget.categoryId,
                    categoryName = categoryName,
                    budget = budget.amount,
                    spent = spent,
                    percentage = percentage,
                    exceeded = exceeded
                )
            )
        }

        _uiState.update {
            it.copy(
                totalBalance = totalBalance,
                totalIncome = totalIncome,
                totalExpenses = totalExpenses,
                categorySpending = categorySpending,
                budgetAlerts = alerts,
                isLoading = false
            )
        }
    }

    private fun getStartOfMonth(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }

    private fun getEndOfMonth(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        return cal.time
    }
}