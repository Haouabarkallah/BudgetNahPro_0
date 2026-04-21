// data/local/dao/BudgetLimitDao.kt
package com.budgetnah.pro.data.local.dao

import androidx.room.*
import com.budgetnah.pro.data.local.entities.BudgetLimit
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetLimitDao {
    @Query("SELECT * FROM budget_limits WHERE userId = :userId")
    fun getBudgetLimitsForUser(userId: Int): Flow<List<BudgetLimit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBudgetLimit(budgetLimit: BudgetLimit): Long

    @Delete
    suspend fun deleteBudgetLimit(budgetLimit: BudgetLimit)
}