package com.budgetnah.pro.domain.usecase.dashboard

import com.budgetnah.pro.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*

class GetLoggedInUserUseCase {
    operator fun invoke(): Flow<User?> = flowOf(User(1, "uuid-123", "test@example.com", "Test User"))
}

class GetExpensesForUserUseCase {
    suspend operator fun invoke(userId: Int, start: Date, end: Date): List<Expense> = emptyList()
}

class GetBudgetLimitsForUserUseCase {
    suspend operator fun invoke(userId: Int): List<BudgetLimit> = emptyList()
}

class GetCategoriesForUserUseCase {
    operator fun invoke(userId: Int): Flow<List<Category>> = flowOf(emptyList())
}

data class Expense(val id: Int, val categoryId: Int, val amount: Double, val date: Date)
data class BudgetLimit(val id: Int, val categoryId: Int, val amount: Double)
data class Category(val id: Int, val name: String, val color: Int)
