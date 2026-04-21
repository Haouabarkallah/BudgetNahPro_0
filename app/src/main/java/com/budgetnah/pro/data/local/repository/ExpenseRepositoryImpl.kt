package com.budgetnah.pro.data.local.repository

import com.budgetnah.pro.data.local.dao.ExpenseDao
import com.budgetnah.pro.data.local.entities.Expense
import com.budgetnah.pro.data.remote.supabase
import com.budgetnah.pro.domain.repository.ExpenseRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow

class ExpenseRepositoryImpl(
    private val dao: ExpenseDao
) : ExpenseRepository {

    override fun getExpenses(userId: Int): Flow<List<Expense>> {
        return dao.getExpensesByUser(userId)
    }

    override suspend fun insertExpense(expense: Expense) {
        // 💾 1. Local
        dao.insertExpense(expense)

        // ☁️ 2. Cloud
        try {
            supabase.from("expenses").insert(expense)
            dao.markAsSynced(expense.id)
        } catch (e: Exception) {
            // ❌ offline → sera sync plus tard
        }
    }

    override suspend fun deleteExpense(expense: Expense) {
        dao.deleteExpense(expense)

        try {
            supabase.from("expenses").delete {
                filter {
                    eq("id", expense.id)
                }
            }
        } catch (e: Exception) {}
    }

    override suspend fun fetchFromCloud(userId: Int) {
        try {
            val remoteExpenses = supabase
                .from("expenses")
                .select()
                .decodeList<Expense>()

            remoteExpenses.forEach {
                dao.insertExpense(it)
            }
        } catch (e: Exception) {
            // Handle error
        }
    }
}
