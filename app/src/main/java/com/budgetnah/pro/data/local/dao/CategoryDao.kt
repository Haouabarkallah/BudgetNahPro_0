// data/local/dao/CategoryDao.kt
package com.budgetnah.pro.data.local.dao

import androidx.room.*
import com.budgetnah.pro.data.local.entities.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories WHERE id IN (SELECT categoryId FROM budget_limits WHERE userId = :userId) OR id IN (SELECT DISTINCT categoryId FROM expenses WHERE userId = :userId)")
    fun getUserCategories(userId: Int): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE userId = :userId")
    fun getAllCategories(userId: Int): Flow<List<Category>>

    @Insert
    suspend fun insertCategory(category: Category): Long

    @Delete
    suspend fun deleteCategory(category: Category)
}

