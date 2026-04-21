// data/local/dao/ExpenseDao.kt
package com.budgetnah.pro.data.local.dao

import androidx.room.*
import com.budgetnah.pro.data.local.entities.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses WHERE userId = :userId ORDER BY date DESC")
    fun getExpensesByUser(userId: Int): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE synced = 0")
    suspend fun getUnsyncedExpenses(): List<Expense>

    @Query("UPDATE expenses SET synced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: String)

    @Insert
    suspend fun insertExpense(expense: Expense): Long

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("""
        SELECT SUM(amount) FROM expenses 
        WHERE userId = :userId 
        AND date BETWEEN :start AND :end
    """)
    suspend fun getTotalExpensesBetween(
        userId: Int,
        start: Long,
        end: Long
    ): Double?

    @Query("""
        SELECT e.categoryId, c.name, SUM(e.amount) as total, c.color 
        FROM expenses e
        JOIN categories c ON e.categoryId = c.id
        WHERE e.userId = :userId 
        AND e.date BETWEEN :start AND :end 
        GROUP BY e.categoryId
    """)
    suspend fun getExpensesByCategory(
        userId: Int,
        start: Long,
        end: Long
    ): List<CategoryExpenseSummary>
}
