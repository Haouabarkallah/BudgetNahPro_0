package com.budgetnah.pro.data.sync

import com.budgetnah.pro.data.local.dao.ExpenseDao
import com.budgetnah.pro.data.local.entities.Expense
import com.budgetnah.pro.data.remote.supabase
import io.github.jan.supabase.postgrest.from

class SyncManager(
    private val dao: ExpenseDao
) {

    suspend fun syncExpenses() {

        val unsynced = dao.getUnsyncedExpenses()

        unsynced.forEach { expense ->
            try {
                supabase.from("expenses").insert(expense)
                dao.markAsSynced(expense.id)
            } catch (e: Exception) {
                // reste en local
            }
        }
    }
}