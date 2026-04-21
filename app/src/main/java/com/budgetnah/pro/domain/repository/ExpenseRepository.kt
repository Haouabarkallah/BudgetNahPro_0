package com.budgetnah.pro.domain.repository

import com.budgetnah.pro.data.local.entities.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getExpenses(userId: Int): Flow<List<Expense>>
    suspend fun insertExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
    suspend fun fetchFromCloud(userId: Int)
}
